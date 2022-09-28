package br.com.app.myFinancy.model;

import lombok.Data;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class UpdateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "{mandatory.name.field}")
    private String name;

    @NotEmpty(message = "{mandatory.login.field}")
    private String login;


    private Double income;

    private String password;

}
