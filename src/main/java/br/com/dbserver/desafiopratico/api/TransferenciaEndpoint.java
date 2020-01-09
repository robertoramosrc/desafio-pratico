package br.com.dbserver.desafiopratico.api;

import br.com.dbserver.desafiopratico.dto.TransferenciaDTO;
import br.com.dbserver.desafiopratico.model.Transferencia;
import br.com.dbserver.desafiopratico.service.TransferenciaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/transferencias")
public class TransferenciaEndpoint {

    private final TransferenciaService transferenciaService;
    private final ModelMapper mapper;

    public TransferenciaEndpoint(TransferenciaService transferenciaService, ModelMapper mapper) {
        this.transferenciaService = transferenciaService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity realizarTransferenciaEntreContasCorrentes(
            @Valid @RequestBody TransferenciaDTO transferenciaDTO){

        Transferencia transferencia = this.transferenciaService.realizarTransferencia(transferenciaDTO);

        return ResponseEntity.ok(transferencia);

    }

}
