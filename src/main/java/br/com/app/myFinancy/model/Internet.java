package br.com.app.myFinancy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.time.LocalDate;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Internet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Value("Conta de internet")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private Double price;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "cd_user")
    @NotNull
    private Users users;

}
