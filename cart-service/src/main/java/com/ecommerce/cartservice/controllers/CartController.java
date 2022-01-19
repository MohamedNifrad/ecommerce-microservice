package com.ecommerce.cartservice.controllers;

import com.ecommerce.cartservice.models.Product;
import com.ecommerce.cartservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/getProducts")
    List<Product> getCartProduct() {
        return productRepository.findAll();
    }

    /**
     * using api gateway
     *
     **/



    /**
     * using rest template
     *
    **/

    @GetMapping("/getAllProducts")
    List<Product> getCartAllProduct() {
        return getAllProduct();
    }

    @Autowired
    RestTemplate restTemplate;

    public List<Product> getAllProduct() {
        final String ROOT_URI = "http://localhost:8045/product/getAll";
        /**
         * if we use api-gate way we can use
         * final String ROOT_URI = "http://localhost:8080/product/getAll"; no need port change
         */

        ResponseEntity<Product[]> response = restTemplate.getForEntity(ROOT_URI, Product[].class);
        return Arrays.asList(response.getBody());
    }
}
