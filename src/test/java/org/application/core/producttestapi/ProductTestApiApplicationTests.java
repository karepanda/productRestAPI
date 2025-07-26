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
    private String baseURL;

    private TestRestTemplate getAuthenticatedRestTemplate() {
        return new TestRestTemplate("admin", "0000");
    }


    @Test
    void testGetProduct() {
        System.out.println(baseURL);
        TestRestTemplate restTemplate = getAuthenticatedRestTemplate();
        Product product = restTemplate.getForObject(baseURL + "6", Product.class);
        assertNotNull(product);
        assertEquals("iPhone 16", product.getName());
    }

    @Test
    void testCreateProduct() {
        TestRestTemplate restTemplate = getAuthenticatedRestTemplate();
        Product product = new Product();
        product.setName("Samsung Mobile");
        product.setDescription("Its Awesome");
        product.setPrice(1000);

        try{
            Product newProduct = restTemplate.postForObject(baseURL, product, Product.class);
            assertEquals(product.getName(), newProduct.getName());
            assertEquals(1000, newProduct.getPrice());
            System.out.println("Product created with ID: " + newProduct.getId());
        }catch (Exception e){
            System.err.println("Error to created product " + e.getMessage());
            throw e;
        }

    }

    @Test
    void testUpdateProduct() {
        TestRestTemplate restTemplate = getAuthenticatedRestTemplate();
        Product product = restTemplate.getForObject(baseURL + "8", Product.class);
        product.setPrice(1400);
        restTemplate.put(baseURL, product);
    }

}