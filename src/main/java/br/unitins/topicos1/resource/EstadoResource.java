package br.unitins.topicos1.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


import br.unitins.topicos1.model.Estado;
import br.unitins.topicos1.repository.EstadoRepository;

@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {
    
    @Inject
    private EstadoRepository repository;

    @GET
    public List<Estado> getAll() {
        
        // seleciona todas as Estados do banco de dados
         return repository.findAll().list();

    }

    @POST
    @Transactional
    public Estado insert(Estado estado) {

        repository.persist(estado);

        return estado;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Estado update(@PathParam("id") Long id, Estado estado) {

         Estado entity = repository.findById(id);

         entity.setNome(estado.getNome());
         entity.setSigla(estado.getSigla());

        return entity;
    }

    @GET
    @Path("/count")
    public long count(){
        return repository.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<Estado> search(@PathParam("nome") String nome){
        return repository.findByNome(nome);
    }
}

