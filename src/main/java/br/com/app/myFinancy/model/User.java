package br.com.app.myFinancy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "{mandatory.name.field}")
    private String name;

    @NotEmpty(message = "{mandatory.login.field}")
    private String login;

    @Email
    @NotEmpty(message = "{mandatory.email.field}")
    private String email;

    @ElementCollection
    private List<BigDecimal> income;

    @NotEmpty(message = "{mandatory.password.field}")
    private String password;

    @Column
    private Boolean isActive;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<WaterBill> listWaterBill;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<EnergyBill> listEnergyBill;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Internet> listInternet;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<CardBill> listCardBill;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<OtherExpenses> listOtherExpenses;

}
