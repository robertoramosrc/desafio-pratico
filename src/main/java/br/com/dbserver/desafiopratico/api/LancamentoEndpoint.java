package br.com.dbserver.desafiopratico.api;

import br.com.dbserver.desafiopratico.dto.LancamentoDTO;
import br.com.dbserver.desafiopratico.service.LancamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lancamentos")

public class LancamentoEndpoint {
    private final LancamentoService lancamentoService;
    private final ModelMapper mapper;

    public LancamentoEndpoint(LancamentoService lancamentoService, ModelMapper mapper) {
        this.lancamentoService = lancamentoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> listar() {

        List<LancamentoDTO> lancamentos = this.lancamentoService.listar()
                .stream()
                .map(l -> this.mapper.map(l, LancamentoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lancamentos);

    }

}
