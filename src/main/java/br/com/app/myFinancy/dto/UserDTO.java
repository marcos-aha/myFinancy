package br.com.app.myFinancy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;

    private String name;

    private String login;

    private String email;

    private List<BigDecimal> income;

    private BigDecimal incomeTotal;
}
