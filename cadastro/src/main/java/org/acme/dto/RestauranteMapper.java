package org.acme.dto;

import org.acme.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
    public Restaurante toRestaurante(AdicionarRestauranteDTO dto);
}
