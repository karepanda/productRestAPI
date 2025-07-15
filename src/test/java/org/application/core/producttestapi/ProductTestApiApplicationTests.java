package org.application.core.producttestapi;

import org.application.core.producttestapi.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTestApiApplicationTests {

    @Value("${producttestapi.services.url}")
    private String baseURl;

    @Test
    public void testCreateProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product newProduct = new Product();
        newProduct.setName("iPhone 16");
        newProduct.setDescription("Latest Apple iPhone");
        newProduct.setPrice(1999);

        Product createdProduct = restTemplate.postForObject(baseURl, newProduct, Product.class);

        assertNotNull(createdProduct);
        assertEquals("iPhone 16", createdProduct.getName());
    }

    @Test
    public void testGetProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product product = restTemplate.getForObject(baseURl + "6", Product.class);

        assertNotNull(product);
        assertEquals("iPhone 16", product.getName());
    }


    @Test
    public void testUpdateProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product product = restTemplate.getForObject(baseURl + "6", Product.class);

        product.setPrice(900);

        restTemplate.put("http://localhost:8080/productapi/products/", product);

    }

}
