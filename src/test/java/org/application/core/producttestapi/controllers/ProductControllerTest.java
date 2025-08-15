package org.application.core.producttestapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.application.core.producttestapi.entities.Product;
import org.application.core.producttestapi.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final int PRODUCT_ID = 1;
    public static final String PRODUCT_NAME = "iPhone 16";
    public static final String PRODUCT_DESCRIPTION = "Its Awesome";
    public static final int PRODUCT_PRICE = 1400;
    public static final String PRODUCT_CONTEXT_PATH = "/productapi";
    public static final String PRODUCTS_URL = "/productapi/products/";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductRepository repository;

    @Test
    public void testFindAllProducts() throws Exception {
        Product product = buildProduct();

        List<Product> products = List.of(product);

        when(repository.findAll()).thenReturn(products);

        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        mockMvc.perform(get(PRODUCTS_URL)
            .contextPath(PRODUCT_CONTEXT_PATH))
            .andExpect(status().isOk())
            .andExpect(content().json(objectWriter.writeValueAsString(products)));
    }

    private static Product buildProduct() {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setPrice(PRODUCT_PRICE);
        return product;
    }

}