package com.example.demo.Registration;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName ;
    private final String lastName ;
    private final String password ;
    private final String email ;



}
