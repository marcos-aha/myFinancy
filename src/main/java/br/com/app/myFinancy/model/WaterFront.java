package br.com.app.myFinancy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class WaterFront implements Serializable {

    private UUID id;

    @Value("Conta de luz")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private Double expenditure;

    private LocalDate dueDate;
    @NotNull
    private UUID users;
}
