package br.com.app.myFinancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InternetFront implements Serializable {
    private UUID id;

    @Value("Conta de luz")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private LocalDate dueDate;

    private UUID users;
}
