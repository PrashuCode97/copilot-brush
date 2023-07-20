package com.stackroute.Copilotdemo.controller;

import com.stackroute.Copilotdemo.model.User;
import com.stackroute.Copilotdemo.repo.UserRepo;
import com.stackroute.Copilotdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("api/v1")
public class DemoController {

    @Autowired
    DemoService service;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/hello")
    public String hello() {
        String name = null;
        String greeting = "Hello, " + name + "!"; // Potential NullPointerException

        return greeting;
    }

    @GetMapping("/divide")
    public int divide() {
        int num1 = 10;
        int num2 = 0;
        int result = service.divide(num1, num2); // Potential ArithmeticException if num2 is 0
        return result;
    }

    @GetMapping("/stocks")
    public Data getStocksByCountry() {
        // Business logic is in controller
        String country = "India";
        RestTemplate restTemplate = new RestTemplate();
        Data stocksData = restTemplate.getForObject("https://api.twelvedata.com/stocks?country=" + country, Data.class);
        return stocksData;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        // Business logic is in controller
        if (user.getOrganization()=="Stackroute"){
            user.setPassword("user@Stackroute");
        } else if (user.getOrganization()=="Wipro") {
            user.setPassword("user@Wipro");
        } else if(user.getOrganization()=="Google"){
            user.setPassword("user@google");
        } else if(user.getOrganization()=="Microsoft"){
            user.setPassword("user@microsoft");
        } else {
            user.setPassword("user@default");
        }
        User userSaved = userRepo.save(user);
        return userSaved;
    }
}
