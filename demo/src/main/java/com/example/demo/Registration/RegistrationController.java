package com.example.demo.Registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationservice;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) throws IllegalAccessException {

        return registrationservice.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm (@RequestParam("token") String token ){
        return registrationservice.confirmToken(token);
    }

}
