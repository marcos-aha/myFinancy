package br.com.app.myFinancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardFront {

    private UUID id;

    @Value("Conta de luz")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private UUID users;
}
