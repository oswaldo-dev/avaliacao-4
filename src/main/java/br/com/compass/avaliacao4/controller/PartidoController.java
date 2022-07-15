package br.com.compass.avaliacao4.controller;

import br.com.compass.avaliacao4.dto.request.RequestPartidoDto;
import br.com.compass.avaliacao4.dto.response.ResponseAssociadoDto;
import br.com.compass.avaliacao4.dto.response.ResponsePartidoDto;
import br.com.compass.avaliacao4.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    private final PartidoService service;

    @Autowired
    public PartidoController(PartidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponsePartidoDto>> get(@RequestParam(required = false) String ideologia) {
        List<ResponsePartidoDto> partidos = service.buscar(ideologia);
        return ResponseEntity.ok(partidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartidoDto> get(@PathVariable Long id) {
        ResponsePartidoDto partido = service.buscar(id);
        return ResponseEntity.ok(partido);
    }

    @GetMapping("{id}/associados")
    public ResponseEntity<List<ResponseAssociadoDto>> getAssociados(@PathVariable Long id) {
        List<ResponseAssociadoDto> associados = service.buscarAssociados(id);

        return ResponseEntity.ok(associados);
    }

    @PostMapping
    public ResponseEntity<ResponsePartidoDto> cadastrar(@RequestBody @Valid RequestPartidoDto partido, UriComponentsBuilder componentsBuilder) {
        ResponsePartidoDto partidoCadastrado = service.cadastrar(partido);
        URI uri = componentsBuilder.path("/partidos/{id}").buildAndExpand(partidoCadastrado.getId()).toUri();
        return ResponseEntity.created(uri).body(partidoCadastrado);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> update(@RequestBody @Valid RequestPartidoDto partido, @PathVariable Long id) {
        service.atualizar(partido, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
