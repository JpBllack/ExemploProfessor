package br.unitins.topicos1.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import br.unitins.topicos1.dto.MunicipioDTO;
import br.unitins.topicos1.dto.MunicipioResponseDTO;
import br.unitins.topicos1.model.Municipio;
import br.unitins.topicos1.repository.EstadoRepository;
import br.unitins.topicos1.repository.MunicipioRepository;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    public List<MunicipioResponseDTO> getAll() {
        List<Municipio> list = municipioRepository.listAll();
        return list.stream().map(MunicipioResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public MunicipioResponseDTO findById(Long id) {
        Municipio municipio = municipioRepository.findById(id);
        if (municipio == null)
            throw new NotFoundException("Município não encontrado.");
        return new MunicipioResponseDTO(municipio);
    }

    @Override
    @Transactional
    public MunicipioResponseDTO create(MunicipioDTO municipioDTO) throws ConstraintViolationException {
        validar(municipioDTO);

        Municipio entity = new Municipio();
        entity.setNome(municipioDTO.getNome());
        entity.setEstado(estadoRepository.findById(municipioDTO.getIdEstado()));
        municipioRepository.persist(entity);

        return new MunicipioResponseDTO(entity);
    }

    @Override
    @Transactional
    public MunicipioResponseDTO update(Long id, MunicipioDTO municipioDTO) throws ConstraintViolationException{
        validar(municipioDTO);
   
        Municipio entity = municipioRepository.findById(id);

        entity.setNome(municipioDTO.getNome());
        entity.setEstado(estadoRepository.findById(municipioDTO.getIdEstado()));

        return new MunicipioResponseDTO(entity);
    }

    private void validar(MunicipioDTO municipioDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<MunicipioDTO>> violations = validator.validate(municipioDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);


    }

    @Override
    @Transactional
    public void delete(Long id) {
        municipioRepository.deleteById(id);
    }

    @Override
    public List<MunicipioResponseDTO> findByNome(String nome) {
        List<Municipio> list = municipioRepository.findByNome(nome);
        return list.stream().map(MunicipioResponseDTO::new).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return municipioRepository.count();
    }

}
