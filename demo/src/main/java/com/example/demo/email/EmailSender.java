package com.example.demo.email;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface EmailSender {
    void send(String to, String email);


}
