package com.wallace.notificationservice.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private String phoneNumber;
    private Double income;
}
