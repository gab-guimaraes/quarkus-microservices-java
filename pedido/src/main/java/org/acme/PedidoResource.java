package org.acme;

import org.bson.types.ObjectId;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/pedido")
public class PedidoResource {

    @POST
    public Response criarPedido(Pedido pedido) {
        pedido.persist();
        return Response.ok(pedido.id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pedido> consultarPedidos() {
        return Pedido.listAll();
    }

    @GET
    @Path("{nomeCliente}")
    public List<Pedido> consultarPedido(@PathParam("nomeCliente") String nomeCliente) {
        return Pedido.list("cliente", nomeCliente);
    }

    @PUT
    @Path("motoboyLocation/{idPedido}")
    public Response alterarLocalizacaoMotoboy(@PathParam("idPedido") String idPedido, Localizacao localizacao) {
        try {
            ObjectId pedidoId = new ObjectId(idPedido);
            Optional<Pedido> pedido = Pedido.findByIdOptional(pedidoId);
            if (pedido.isPresent()) {
                pedido.get().localizacaoEntregador = localizacao;
                pedido.get().persistOrUpdate();
                return Response.ok().build();
            } else {
                return Response.status(400,"Pedido nao encontrado").build();
            }
        } catch (Exception e) {
            return Response.status(500, "id invalido").build();
        }
    }
}