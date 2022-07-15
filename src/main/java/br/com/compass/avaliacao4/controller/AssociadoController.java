package br.com.compass.avaliacao4.controller;

import br.com.compass.avaliacao4.dto.request.RequestAssociadoDto;
import br.com.compass.avaliacao4.dto.request.RequestVinculoDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociadoDto;
import br.com.compass.avaliacao4.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    private final AssociadoService service;

    @Autowired
    public AssociadoController(AssociadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponseAssociadoDto>> get(@RequestParam(required = false) String cargo,
                                                          @RequestParam(required = false, defaultValue = "id") String sort) {
        List<ResponseAssociadoDto> associados = service.buscar(cargo, sort);

        return ResponseEntity.ok(associados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociadoDto> get(@PathVariable Long id) {
        ResponseAssociadoDto associado = service.buscar(id);

        return ResponseEntity.ok(associado);
    }

    @PostMapping
    public ResponseEntity<ResponseAssociadoDto> post(@RequestBody @Valid RequestAssociadoDto associado, UriComponentsBuilder componentsBuilder) {
        ResponseAssociadoDto associadoCadastrado = service.cadastrar(associado);
        URI uri = componentsBuilder.path("/associados/{id}").buildAndExpand(associadoCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(associadoCadastrado);
    }

    @PostMapping("/partidos")
    public ResponseEntity<Void> post(@RequestBody @Valid RequestVinculoDto vinculo) {
        service.vinculaPartido(vinculo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid RequestAssociadoDto associado, @PathVariable Long id) {
        service.atualizar(associado, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idAssociado}/partidos/{idPartido}")
    public ResponseEntity<Void> delete(@PathVariable Long idAssociado, @PathVariable Long idPartido) {
        service.demitir(idAssociado, idPartido);

        return ResponseEntity.noContent().build();
    }
}
