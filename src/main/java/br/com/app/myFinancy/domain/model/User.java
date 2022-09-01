package br.com.app.myFinancy.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @ElementCollection
    private List<BigDecimal> income;

    @NotEmpty(message = "{mandatory.password.field}")
    private String password;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<WaterBill> listWaterBill;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<EnergyBill> listEnergyBill;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Internet> listInternet;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<CardBill> listCardBill;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<OtherExpenses> listOtherExpenses;

}
