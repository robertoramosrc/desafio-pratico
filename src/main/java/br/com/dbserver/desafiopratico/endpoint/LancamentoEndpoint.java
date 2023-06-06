package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.dto.LancamentoDTO;
import br.com.dbserver.desafiopratico.service.LancamentoService;
import io.swagger.v3.oas.annotations.Operation;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
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

    @Operation(description = "Consultar lancamentos [Conta Corrente é opcional]")
    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> listar(
            @RequestParam("conta") Optional<Long> conta ) {

        List<LancamentoDTO> lancamentos = this.lancamentoService.listar(conta)
                .stream()
                .map(l -> this.mapper.map(l, LancamentoDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lancamentos);

    }

}
