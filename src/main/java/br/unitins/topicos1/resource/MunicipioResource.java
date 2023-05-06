package br.unitins.topicos1.resource;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import com.oracle.svm.core.annotate.Delete;

import br.unitins.topicos1.application.Result;
import br.unitins.topicos1.dto.MunicipioDTO;
import br.unitins.topicos1.dto.MunicipioResponseDTO;
import br.unitins.topicos1.service.MunicipioService;

@Path("/municipios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MunicipioResource {
    
    @Inject
    MunicipioService municipioService;

    @GET
    public List<MunicipioResponseDTO> getAll() {
        return municipioService.getAll();
    }

    @GET
    @Path("/{id}")
    public MunicipioResponseDTO findById(@PathParam("id") Long id) {
        return municipioService.findById(id);
    }

    @POST
    public Response insert(MunicipioDTO dto) {
        try {
            MunicipioResponseDTO municipio = municipioService.create(dto);
            return Response.status(Status.CREATED).entity(municipio).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MunicipioDTO dto) {
        try {
            MunicipioResponseDTO municipio = municipioService.update(id, dto);
            return Response.ok(municipio).build();
        } catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }      
    }

    @Delete
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        municipioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }


    @GET
    @Path("/count")
    public long count(){
        return municipioService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<MunicipioResponseDTO> search(@PathParam("nome") String nome){
        return municipioService.findByNome(nome);
        
    }
}

