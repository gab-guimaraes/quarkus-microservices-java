package org.johnwick182;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/prato")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PratoResource {

    @GET
    public Uni<List<PanacheEntityBase>> buscarPratos() {
        return Prato.listAll();
    }
}