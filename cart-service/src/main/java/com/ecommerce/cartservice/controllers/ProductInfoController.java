package com.ecommerce.cartservice.controllers;

import com.ecommerce.cartservice.models.Product;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class ProductInfoController {
    /**
     * using api-gateway and eureka server and eureka client
     */

    @Autowired
    private RestTemplate restTemplate;

    private EurekaClient eurekaClient;

    @Value("${spring.cloud.client.hostname}")
    private String applicationURL;

    @GetMapping("/my-get-products")
    List<Product> getCartProduct() {
//        Application application = eurekaClient.getApplication(employeeSearchServiceId);
//        InstanceInfo instanceInfo = application.getInstances().get(0);
//        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "employee/find/";
//        System.out.println("URL" + url);
        System.out.println("URL" + applicationURL);
        return getAllProductHost();
    }

    public List<Product> getAllProductHost() {
        final String ROOT_URI = applicationURL+":8080";
        ResponseEntity<Product[]> response = restTemplate.getForEntity(ROOT_URI, Product[].class);
        return Arrays.asList(response.getBody());
    }
}
