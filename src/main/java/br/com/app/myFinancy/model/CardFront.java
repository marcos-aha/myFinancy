package br.com.app.myFinancy.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardFront implements Serializable {

    private UUID id;

    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private LocalDate dueDate;

    private LocalDate closingDate;

    private UUID users;
}
