package br.unitins.topicos1.resource;

import java.util.List;
import java.util.stream.Collectors;

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

import br.unitins.topicos1.dto.MunicipioDTO;
import br.unitins.topicos1.dto.MunicipioResponseDTO;
import br.unitins.topicos1.model.Municipio;
import br.unitins.topicos1.repository.EstadoRepository;
import br.unitins.topicos1.repository.MunicipioRepository;

@Path("/municipios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MunicipioResource {
    
    @Inject
    private MunicipioRepository repository;

    @Inject
    private EstadoRepository estadoRepository;

    @GET
    public List<MunicipioResponseDTO> getAll() {
        
        // seleciona todas as Municipios do banco de dados

        return repository.findAll()
            .stream()
            .map(municipio -> new MunicipioResponseDTO(municipio))
            .collect(Collectors.toList());
            
        // return repository.findAll().list();

    }

    @POST
    @Transactional
    public MunicipioResponseDTO insert(MunicipioDTO dto) {

        Municipio entity = new Municipio();
        entity.setNome(dto.getNome());
        entity.setEstado(estadoRepository.findById(dto.getIdEstado()));

        repository.persist(entity);

        return new MunicipioResponseDTO(entity);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Municipio update(@PathParam("id") Long id, Municipio municipio) {

         Municipio entity = repository.findById(id);

         entity.setNome(municipio.getNome());

        return entity;
    }

    @GET
    @Path("/count")
    public long count(){
        return repository.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<Municipio> search(@PathParam("nome") String nome){
        return repository.findByNome(nome);
    }
}

