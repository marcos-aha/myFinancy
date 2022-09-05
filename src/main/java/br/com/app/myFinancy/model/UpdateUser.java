package br.com.app.myFinancy.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;
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

    @ElementCollection
    private List<BigDecimal> income;

    private String newPassword;

    private String oldPassword;


}
