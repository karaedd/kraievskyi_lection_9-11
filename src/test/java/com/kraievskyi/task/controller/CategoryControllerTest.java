package com.kraievskyi.task.controller;

import com.kraievskyi.task.model.Category;
import com.kraievskyi.task.service.CategoryService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldShowAllCategories() {
        List<Category> mockCategories = List.of(
                new Category(1L, "milk"),
                new Category(2L, "butter"),
                new Category(3L, "cheese"));
        Mockito.when(categoryService.getAllCategory()).thenReturn(mockCategories);

        RestAssuredMockMvc.when()
                .get("/category")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("milk"))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("butter"))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].name", Matchers.equalTo("cheese"));
    }
}
