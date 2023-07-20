package com.stackroute.Copilotdemo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public int divide(int num1, int num2) {
        int result = num1 / num2; // Potential ArithmeticException if num2 is 0

        return result;
    }
}
