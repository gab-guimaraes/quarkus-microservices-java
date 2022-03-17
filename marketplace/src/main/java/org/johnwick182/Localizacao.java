package org.johnwick182;

import javax.persistence.*;

@Entity
@Cacheable
@Table(name = "localizacao")
public class Localizacao {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Long id;
        public Double latitude;
        public Double longitute;
}
