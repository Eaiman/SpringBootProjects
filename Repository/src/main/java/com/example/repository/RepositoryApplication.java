package com.example.repository;

import com.example.repository.model.Product;
import com.example.repository.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RepositoryApplication implements CommandLineRunner {

    private ProductRepository productRepository;
    private Logger LOG = LoggerFactory.getLogger(RepositoryApplication.class);

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RepositoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product();
        product1.setName("Tester product");
        product1.setDescription("This is a tester product");
        product1.setCategory("Test");
        product1.setType("General");
        product1.setPrice(0.0);

        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Another Tester product");
        product2.setDescription("This is a tester product");
        product2.setCategory("Test");
        product2.setType("Custom");
        product2.setPrice(15.0);

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Tester product");
        product3.setDescription("description");
        product3.setCategory("Test");
        product3.setType("specific");
        product3.setPrice(19.0);

        productRepository.save(product3);

//        productRepository.delete(product3);

        Product foundProduct = productRepository.findByType("General");

        if (foundProduct != null) {
            LOG.info("Product count: " + productRepository.count());
            productRepository.delete(foundProduct);
            LOG.info("Product is deleted");
            LOG.info("Product count: " + productRepository.count());
        }

//        Product productToUpdate = productRepository.findByType("specific");
//
//        if (productToUpdate != null) {
//            LOG.info("Before update product details: " + productToUpdate.toString());
//            productToUpdate.setName("Updated Product");
//            productToUpdate.setDescription("Updated Description");
//
//            Product updated = productRepository.save(productToUpdate);
//            LOG.info("Updated product details: " + updated.toString());
//        }
//
//        productRepository.findAll().stream().forEach(product -> LOG.info("Product found: " + product.toString()));

//        Product resultProduct = productRepository.findByType("Custom");
//
//        LOG.info("General type of Product found: " + resultProduct.toString());

//        productRepository.findByDescriptionAndCategory("This is a tester product", "Test")
//                .stream()
//                .forEach(product -> LOG.info("Product found: " + product.toString()));

//        List<String> names = new ArrayList<>();
//        names.add("Tester product");
//        names.add("Another Tester product");

//        productRepository.findByCategoryAndNameIn("Test", names)
//                .stream()
//                .forEach(product -> LOG.info("Matching product for findByCategoryAndNameIn: " + product.toString()));
    }
}
