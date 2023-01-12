package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.ProductRequestDto;
import com.kraievskyi.task.model.Category;
import com.kraievskyi.task.model.Product;
import com.kraievskyi.task.service.ProductService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldShowAllProducts() {
        List<Product> mockProducts = List.of(
                new Product(1L, "yagotynske", 37.53,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 9),
                        LocalDate.of(2023,1, 17)),
                new Product(2L, "galychyna", 38.62,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 10),
                        LocalDate.of(2023, 1, 18)),
                new Product(3L, "ferma", 36.65,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023,1, 19)));
        Mockito.when(productService.getAllProducts()).thenReturn(mockProducts);

        RestAssuredMockMvc.when()
                .get("/product")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("yagotynske"))
                .body("[0].price" , Matchers.equalTo(37.53f))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("galychyna"))
                .body("[1].price" , Matchers.equalTo(38.62f))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].name", Matchers.equalTo("ferma"))
                .body("[2].price" , Matchers.equalTo(36.65f));
    }

    @Test
    public void shouldReturnAllByCategoryIdAndName() {
        List<Product> mockProducts = List.of(
                new Product(1L, "ferma", 36.65,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023,1, 19)),
                new Product(2L, "ferma", 37.45,
                        new Category(1L, "milk"), LocalDate.of(2023, 2, 1),
                        LocalDate.of(2023,1, 22)),
                new Product(3L, "ferma", 38.35,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 5),
                        LocalDate.of(2023,1, 19)));

        Mockito.when(productService.findAllByCategoryIdAndName(1L, "ferma",
                PageRequest.of(0, 10))).thenReturn(mockProducts);

        RestAssuredMockMvc.given()
                .queryParam("name", "ferma")
                .queryParam("count", 10)
                .queryParam("page", 0)
                .when()
                .get("/product/category/{id}", 1)
                .then()
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("ferma"))
                .body("[0].price", Matchers.equalTo(36.65f))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("ferma"))
                .body("[1].price", Matchers.equalTo(37.45f))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].name", Matchers.equalTo("ferma"))
                .body("[2].price", Matchers.equalTo(38.35f));
    }

    @Test
    public void shouldCreateProduct() {
        Product productToSave = new Product("ferma", 36.65,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023,1, 19));
        Mockito.when(productService.create(productToSave))
                .thenReturn(new Product(6L, "ferma", 36.65,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023,1, 19)));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProductRequestDto(
                        productToSave.getName(),
                        productToSave.getPrice(),
                        productToSave.getCategory().getId(),
                        productToSave.getDateManufacture(),
                        productToSave.getDateExpire()))
                .when()
                .post("/product")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(6))
                .body("name", Matchers.equalTo("ferma"))
                .body("price", Matchers.equalTo(36.65f))
                .body("categoryId", Matchers.equalTo(1));
    }
}
