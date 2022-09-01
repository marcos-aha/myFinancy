package br.com.app.myFinancy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WaterBill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Value("Conta de água")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private BigDecimal price;

    private Double expenditure;

    @NotEmpty(message = "{mandatory.date.field}")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @ManyToOne
    @JoinColumn(name = "cd_user")
    private User users;
}
