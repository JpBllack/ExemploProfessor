package br.unitins.topicos1.resource;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


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

