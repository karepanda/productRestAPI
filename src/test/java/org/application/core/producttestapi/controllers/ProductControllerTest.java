package org.application.core.producttestapi.controllers;

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

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductRepository repository;

    @Test
    public void testFindAllProducts() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setName("iPhone 16");
        product.setDescription("Its Awesome");
        product.setPrice(1400);

        List<Product> products = List.of(product);

        when(repository.findAll()).thenReturn(products);

        mockMvc.perform(get("/productapi/products/")
            .contextPath("/productapi"))
            .andExpect(status().isOk());
    }

}