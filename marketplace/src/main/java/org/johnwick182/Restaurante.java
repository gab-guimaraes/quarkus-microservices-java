package org.johnwick182;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.*;

@Entity
@Cacheable
@Table(name = "restaurante")
public class Restaurante extends PanacheEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;

    @OneToOne
    public Localizacao localizacao;
}
