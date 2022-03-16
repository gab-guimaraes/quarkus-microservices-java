package org.acme;

import org.acme.dto.AdicionarRestauranteDTO;
import org.acme.dto.RestauranteMapper;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.*;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows =
@OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
public class RestaurantResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Tag(name="restaurante")
    public List<Restaurante> buscar() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    @Tag(name="restaurante")
    public Response adicionar(@Valid AdicionarRestauranteDTO dto) {
        Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        Restaurante.persist(restaurante);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name="restaurante")
    public void atualizar(@PathParam("id") Long id, Restaurante dto) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        if (restauranteOp.isEmpty()) {
            throw new NotFoundException();
        }
        Restaurante restaurante = restauranteOp.get();
        restaurante.nome = dto.nome;
        Restaurante.persist(restaurante);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name="restaurante")
    public void deletar(@PathParam("id") Long id) {
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
            throw new NotFoundException();
        } );
    }


    @POST
    @Transactional
    @Tag(name="prato")
    @Path("{idRestaurante}/pratos")
    public Response adicionarPrato(@PathParam("idRestaurante") Long id, Prato prato) {
        Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);
        if (restauranteOptional.isEmpty())
            throw new NotFoundException();
        prato.restaurante = restauranteOptional.get();
        Prato.persist(prato);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name="prato")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long id) {
        Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(id);
        if (restauranteOptional.isEmpty())
            throw new NotFoundException();

        return Prato.list("restaurante", restauranteOptional.get());
    }

    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name="prato")
    public void delete(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
        Optional<Restaurante> restauranteOptional = Restaurante.findByIdOptional(idRestaurante);
        if (restauranteOptional.isEmpty())
            throw new NotFoundException();

        Optional<Prato> pratoOp = Prato.findByIdOptional(id);

        pratoOp.ifPresentOrElse(Prato::delete, () -> {
            throw new NotFoundException();
        });
    }

}