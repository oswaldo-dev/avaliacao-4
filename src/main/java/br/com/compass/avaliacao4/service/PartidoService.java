package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.dto.request.RequestPartidoDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociadoDto;
import br.com.compass.avaliacao4.dto.response.ResponsePartidoDto;
import br.com.compass.avaliacao4.entities.Associado;
import br.com.compass.avaliacao4.entities.Partido;
import br.com.compass.avaliacao4.enums.Ideologia;
import br.com.compass.avaliacao4.exceptions.IdeologiaNotFoundException;
import br.com.compass.avaliacao4.exceptions.PartidoNotFoundException;
import br.com.compass.avaliacao4.repository.AssociadoRepository;
import br.com.compass.avaliacao4.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {


    private final PartidoRepository partidoRepository;
    private final AssociadoRepository associadoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartidoService(PartidoRepository partidoRepository, ModelMapper modelMapper, AssociadoRepository associadoRepository) {
        this.partidoRepository = partidoRepository;
        this.modelMapper = modelMapper;
        this.associadoRepository = associadoRepository;
    }

    public List<ResponsePartidoDto> buscar(String ideologia) {
        List<Partido> partidos = partidoRepository.findWithFilters(ideologia);
        return partidos.stream().map(partido -> modelMapper.map(partido, ResponsePartidoDto.class))
                .collect(Collectors.toList());
    }

    public ResponsePartidoDto buscar(Long id) {
        Partido entidade = partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        return modelMapper.map(entidade, ResponsePartidoDto.class);
    }

    public ResponsePartidoDto cadastrar(RequestPartidoDto partido) {
        partido.setIdeologia(validacaoDeIdeologia(partido.getIdeologia()));
        Partido entidade = modelMapper.map(partido, Partido.class);
        Partido entidadeSalva = partidoRepository.save(entidade);
        return modelMapper.map(entidadeSalva, ResponsePartidoDto.class);
    }

    public void atualizar(RequestPartidoDto partido, Long id) {
        partido.setIdeologia(validacaoDeIdeologia(partido.getIdeologia()));
        Partido entidade = partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        modelMapper.map(partido, entidade);
        partidoRepository.save(entidade);
    }

    public void deletar(Long id) {
        Partido entidade = partidoRepository.findById(id).orElseThrow(PartidoNotFoundException::new);
        partidoRepository.delete(entidade);
    }

    public String validacaoDeIdeologia(String ideologia) {
        try {
            return Ideologia.valueOf(ideologia.toUpperCase()).getValor();
        } catch (IllegalArgumentException e) {
            throw new IdeologiaNotFoundException();
        }
    }

    public List<ResponseAssociadoDto> buscarAssociados(Long id) {
        List<Associado> associados = associadoRepository.findByPartido_Id(id);
        return associados.stream().map(associado -> modelMapper.map(associado, ResponseAssociadoDto.class))
                .collect(Collectors.toList());
    }
}
