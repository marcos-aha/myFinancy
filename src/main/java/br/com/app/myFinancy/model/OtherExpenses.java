package br.com.app.myFinancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
public class OtherExpenses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Length(max = 20)
    @NotEmpty(message = "{mandatory.description.field}")
    private String description;

    @NotNull(message = "{mandatory.price.field}")
    private BigDecimal price;

    @NotEmpty(message = "{mandatory.date.field}")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "cd_user")
    private User users;
}
