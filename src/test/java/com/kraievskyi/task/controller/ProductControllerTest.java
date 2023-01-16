package com.kraievskyi.task.controller;

import com.kraievskyi.task.dto.ProductRequestDto;
import com.kraievskyi.task.dto.ProductResponseDto;
import com.kraievskyi.task.dto.mapper.ProductMapper;
import com.kraievskyi.task.model.Category;
import com.kraievskyi.task.model.Product;
import com.kraievskyi.task.service.ProductService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.time.LocalDate;
import java.util.List;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @MockBean
    private ProductMapper productMapper;
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
                        LocalDate.of(2023, 1, 17)),
                new Product(2L, "galychyna", 38.62,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 10),
                        LocalDate.of(2023, 1, 18)),
                new Product(3L, "ferma", 36.65,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023, 1, 19)));
        Mockito.when(productService.getAllProducts()).thenReturn(mockProducts);

        RestAssuredMockMvc.when()
                .get("/product")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3));
    }

    @Test
    public void shouldReturnAllByCategoryIdAndName() {
        List<Product> mockProducts = List.of(
                new Product(1L, "ferma", 36.65,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023, 1, 19)),
                new Product(2L, "ferma", 37.45,
                        new Category(1L, "milk"), LocalDate.of(2023, 2, 1),
                        LocalDate.of(2023, 1, 22)),
                new Product(3L, "ferma", 38.35,
                        new Category(1L, "milk"), LocalDate.of(2023, 1, 5),
                        LocalDate.of(2023, 1, 19)));

        Mockito.when(productService.findAllByCategoryIdAndName(1L, "ferma",
                PageRequest.of(0, 10))).thenReturn(mockProducts);

        RestAssuredMockMvc.given()
                .queryParam("name", "ferma")
                .queryParam("count", 10)
                .queryParam("page", 0)
                .when()
                .get("/product/category/{id}", 1)
                .then()
                .body("size()", Matchers.equalTo(3));
    }

    @Test
    public void shouldCreateProduct() {
        Product productToSave = new Product("ferma", 36.65,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 19));
        Mockito.when(productMapper.toProductResponseDto(productService.save(productToSave)))
                .thenReturn(new ProductResponseDto(6L, "ferma", 36.65,
                        1L, LocalDate.of(2023, 1, 11),
                        LocalDate.of(2023, 1, 19)));

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
                .statusCode(201)
                .body("id", Matchers.equalTo(6))
                .body("name", Matchers.equalTo("ferma"))
                .body("price", Matchers.equalTo(36.65f))
                .body("categoryId", Matchers.equalTo(1))
                .body("dateManufacture", Matchers.equalTo("2023-01-11"))
                .body("dateExpire", Matchers.equalTo("2023-01-19"));
    }

    @Test
    public void shouldNotCreateProductWithoutName() {
        Product productToSave = new Product("", 36.65,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 19));

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
                .statusCode(400);
    }

    @Test
    public void shouldNotCreateProductWithNegativePrice() {
        Product productToSave = new Product("ferma", -1.0,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 19));

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
                .statusCode(400);
    }

    @Test
    public void shouldUpdateProductName() {
        RestAssuredMockMvc.given()
                .queryParam("name", "milk")
                .when()
                .patch("product/{id}", 1)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldUpdateProduct() {
        Product product = new Product("ferma", 36.65,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 19));
        Mockito.when(productService.updateProduct(1L, product)).thenReturn(product);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new ProductRequestDto(product.getName(),
                        product.getPrice(), product.getCategory().getId(),
                        product.getDateManufacture(), product.getDateExpire()))
                .when()
                .put("product/{id}", 1)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldDeleteProduct() {
        RestAssuredMockMvc.given()
                .when()
                .delete("product/{id}", 1)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetProductById() {
        Product mockProduct = new Product(7L,"ferma", 36.65,
                new Category(1L, "milk"), LocalDate.of(2023, 1, 11),
                LocalDate.of(2023, 1, 19));
        Mockito.when(productService.getById(7L)).thenReturn(mockProduct);
        RestAssuredMockMvc.given()
                .when()
                .get("product/{id}", 1)
                .then()
                .statusCode(200);
    }
}
