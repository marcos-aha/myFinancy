package br.com.app.myFinancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtherExpensesFront {

    private UUID id;

    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private LocalDate buyDate;

    private UUID users;
}
