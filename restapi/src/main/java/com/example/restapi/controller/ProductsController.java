package com.example.restapi.controller;

import com.example.restapi.model.Product;
import com.example.restapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/products/")
public class ProductsController {

    private Logger LOG = LoggerFactory.getLogger(ProductRepository.class);

    private ProductRepository productRepository;

    @Autowired
    public void productRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") String id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        } else {
            LOG.info("No product found with given id");
            return new Product();
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product saveProduct(@RequestBody Product productToSave) {
        return productRepository.save(productToSave);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Product updateProduct(@PathVariable(name = "id") String id, @RequestBody Product productToUpdate) {
        if (productRepository.findById(id).isPresent()) {
            Product foundProduct = productRepository.findById(id).get();

            foundProduct.setPrice(productToUpdate.getPrice());
            foundProduct.setType(productToUpdate.getType());
            foundProduct.setCategory(productToUpdate.getCategory());
            foundProduct.setDescription(productToUpdate.getDescription());
            foundProduct.setName(productToUpdate.getName());

            return productRepository.save(foundProduct);
        } else {
            LOG.info("No product found to update with given id");
            return productToUpdate;
        }
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") String id) {
//        productRepository.deleteById(id);
        if (productRepository.findById(id).isPresent()) {
            Product foundProduct = productRepository.findById(id).get();
            productRepository.delete(foundProduct);
        } else {
            LOG.info("No product found to update with given id");
        }
    }
}
