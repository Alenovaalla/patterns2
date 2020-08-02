package ru.netology.rest;
import lombok.Value;

@Value
public class Registration {

    private String login;
    private String password;
    private String status;
}
