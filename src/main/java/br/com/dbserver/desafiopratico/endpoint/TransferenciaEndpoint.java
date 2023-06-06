package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.dto.TransferenciaDTO;
import br.com.dbserver.desafiopratico.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaEndpoint {

    private final TransferenciaService transferenciaService;
    private final ModelMapper mapper;

    public TransferenciaEndpoint(TransferenciaService transferenciaService, ModelMapper mapper) {
        this.transferenciaService = transferenciaService;
        this.mapper = mapper;
    }

    @Operation(description = "Realiza a transferência de valores entre duas contas")
    @PostMapping
    public ResponseEntity realizarTransferenciaEntreContasCorrentes(
            @Validated @RequestBody TransferenciaDTO transferenciaDTO){

        this.transferenciaService.realizarTransferencia(transferenciaDTO);

        return ResponseEntity.ok(transferenciaDTO);

    }

}
