package com.example.demo.Registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class emailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return true;
    }
}
