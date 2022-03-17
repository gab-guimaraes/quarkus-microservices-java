package org.johnwick182;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Cacheable
public class Prato extends PanacheEntity {
    public Long id;
    public String nome;
    public String descricao;
    @ManyToOne
    public Restaurante restaurante;
    public BigDecimal preco;
}
