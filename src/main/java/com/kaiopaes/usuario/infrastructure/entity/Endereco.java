package com.kaiopaes.usuario.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private Long number;
    @Column(name = "complement", length = 10)
    private String complement;
    @Column(name = "city", length = 150)
    private String city;
    @Column(name = "state", length = 2)
    private String state;
    @Column(name = "cep", length = 9)
    private String cep;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")

    private List<Telefone> telefones;

}
