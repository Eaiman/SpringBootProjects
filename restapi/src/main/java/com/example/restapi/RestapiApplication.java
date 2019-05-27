package com.example.restapi;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestapiApplication implements CommandLineRunner {

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product testProduct = new Product();
        testProduct.setName("Simple Product");
        testProduct.setDescription("This is a tester product");
        testProduct.setCategory("SPECIAL");
        testProduct.setType("CUSTOM");
        testProduct.setPrice(10.0);

        productRepository.save(testProduct);
    }
}
