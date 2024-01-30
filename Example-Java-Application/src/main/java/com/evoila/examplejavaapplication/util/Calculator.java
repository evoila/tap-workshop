package com.evoila.examplejavaapplication.util;

import org.springframework.stereotype.Component;

@Component("calculator")
public class Calculator {

    public Integer add(Integer first, Integer second) {
        return first + second;
    }
}
