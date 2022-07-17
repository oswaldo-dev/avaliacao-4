package br.com.compass.avaliacao4.service;

import br.com.compass.avaliacao4.dto.request.RequestAssociadoDto;
import br.com.compass.avaliacao4.dto.request.RequestVinculoDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociadoDto;
import br.com.compass.avaliacao4.entities.Associado;
import br.com.compass.avaliacao4.entities.Partido;
import br.com.compass.avaliacao4.enums.CargoPolitico;
import br.com.compass.avaliacao4.enums.Sexo;
import br.com.compass.avaliacao4.exceptions.*;
import br.com.compass.avaliacao4.repository.AssociadoRepository;
import br.com.compass.avaliacao4.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final PartidoRepository partidoRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository, ModelMapper modelMapper, PartidoRepository partidoRepository) {
        this.associadoRepository = associadoRepository;
        this.modelMapper = modelMapper;
        this.partidoRepository = partidoRepository;
    }

    public List<ResponseAssociadoDto> buscar(String cargo, String sort) {
        if (!Objects.equals(cargo, "Presidente")
                && !Objects.equals(cargo, "Vereador")
                && !Objects.equals(cargo, "Prefeito")
                && !Objects.equals(cargo, "Deputado-Federal")
                && !Objects.equals(cargo, "Deputado-Estadual")
                && !Objects.equals(cargo, "Senado")
                && !Objects.equals(cargo, "Governador")
                && !Objects.equals(cargo, "Nenhum")) {
            throw new CargoPoliticoNotFoundException();
        }
        List<Associado> associados = associadoRepository.findWithFilters(cargo, Sort.by(Sort.Direction.ASC, sort));
        return associados.stream().map(associado -> modelMapper.map(associado, ResponseAssociadoDto.class))
                .collect(Collectors.toList());
    }

    public ResponseAssociadoDto buscar(Long id) {
        Associado entidade = associadoRepository.findById(id).orElseThrow(AssociadoNotFoundException::new);
        return modelMapper.map(entidade, ResponseAssociadoDto.class);
    }

    public ResponseAssociadoDto cadastrar(RequestAssociadoDto associado) {
        associado.setCargoPolitico(validacaoDeCargoPolitico(associado.getCargoPolitico()));
        associado.setSexo(validacaoDeSexo(associado.getSexo()));
        Associado entidade = modelMapper.map(associado, Associado.class);
        Associado entidadeSalva = associadoRepository.save(entidade);
        return modelMapper.map(entidadeSalva, ResponseAssociadoDto.class);
    }

    public void atualizar(RequestAssociadoDto associado, Long id) {
        associado.setCargoPolitico(validacaoDeCargoPolitico(associado.getCargoPolitico()));
        associado.setSexo(validacaoDeSexo(associado.getSexo()));
        Associado entidade = associadoRepository.findById(id).orElseThrow(AssociadoNotFoundException::new);
        modelMapper.map(associado, entidade);
        associadoRepository.save(entidade);
    }

    public void deletar(Long id) {
        Associado entidade = associadoRepository.findById(id).orElseThrow(AssociadoNotFoundException::new);
        associadoRepository.delete(entidade);
    }

    public String validacaoDeCargoPolitico(String cargoPolitico) {
        try {
            return CargoPolitico.valueOf(cargoPolitico.toUpperCase()).getValor();
        } catch (IllegalArgumentException e) {
            throw new CargoPoliticoNotFoundException();
        }
    }
    public String validacaoDeSexo(String sexo) {
        try {
            return Sexo.valueOf(sexo.toUpperCase()).getValor();
        } catch (IllegalArgumentException e) {
            throw new SexoNotFoundException();
        }
    }

    public void vinculaPartido(RequestVinculoDto vinculo) {
        Associado associado = associadoRepository.findById(vinculo.getIdAssociado()).orElseThrow(AssociadoNotFoundException::new);
        Partido partido = partidoRepository.findById(vinculo.getIdPartido()).orElseThrow(PartidoNotFoundException::new);

        associado.setPartido(partido);
        associadoRepository.save(associado);
    }

    public void demitir(Long idAssociado, Long idPartido) {
        Associado associado = associadoRepository.findById(idAssociado).orElseThrow(AssociadoNotFoundException::new);
        partidoRepository.findById(idPartido).orElseThrow(PartidoNotFoundException::new);

        associado.setPartido(null);
        associadoRepository.save(associado);
    }
}
