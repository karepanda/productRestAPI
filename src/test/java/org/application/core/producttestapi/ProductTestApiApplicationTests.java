package org.application.core.producttestapi;

import org.application.core.producttestapi.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTestApiApplicationTests {


    @Test
    public void testGetProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product product = restTemplate.getForObject("http://localhost:8080/products/1", Product.class);

        assertNotNull(product);
        assertEquals("MacBook pro air", product.getName());
    }

    @Test
    public void testCreateProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product newProduct = new Product();
        newProduct.setName("iPhone 14");
        newProduct.setDescription("Latest Apple iPhone");
        newProduct.setPrice(999);

        Product createdProduct = restTemplate.postForObject("http://localhost:8080/productapi/products/", newProduct, Product.class);

        assertNotNull(createdProduct);
        assertEquals("iPhone 14", createdProduct.getName());
    }

    @Test
    public void testUpdateProduct() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        Product product = restTemplate.getForObject("http://localhost:8080/productapi/products/1", Product.class);

        product.setPrice(1200);

        restTemplate.put("http://localhost:8080/productapi/products/", product);

    }

}
