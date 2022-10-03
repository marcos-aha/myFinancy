package br.com.app.myFinancy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardBill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Length(max = 20)
    @NotEmpty(message = "{mandatory.description.field}")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private LocalDate dueDate;

    private LocalDate closingDate;

    @ManyToOne
    @JoinColumn(name = "cd_user")
    private Users users;
}
