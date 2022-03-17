package org.johnwick182;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @GET
    @Path("{idRestaurante}/pratos")
    public Uni<List<PanacheEntityBase>> buscarPratos(@PathParam("idRestaurante") Long id) {
        Uni<PanacheEntityBase> restauranteOptional = Restaurante.findById(id);

        return Prato.list("restaurante", restauranteOptional);
    }

}
