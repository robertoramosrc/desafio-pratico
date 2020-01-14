package br.com.dbserver.desafiopratico.endpoint;

import br.com.dbserver.desafiopratico.dto.ContaCorrenteDTO;
import br.com.dbserver.desafiopratico.model.ContaCorrente;
import br.com.dbserver.desafiopratico.service.ContaCorrenteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
@Validated
public class ContaCorrenteEndpoint {

    private final ContaCorrenteService contaCorrenteService;
    private final ModelMapper mapper;

    public ContaCorrenteEndpoint(ContaCorrenteService contaCorrenteService, ModelMapper mapper) {
        this.contaCorrenteService = contaCorrenteService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation("Consultar Conta Cossrente pelo Número/Agencia ou sem filtro. ")
    public ResponseEntity<List<ContaCorrenteDTO>> listar(
            @RequestParam("agencia") Optional<Long> agencia,
            @RequestParam("conta") Optional<Long> conta) {

        List<ContaCorrenteDTO> contas = this.contaCorrenteService.listar(agencia, conta)
                .stream()
                .map(cc -> this.mapper.map(cc, ContaCorrenteDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(contas);

    }

    @GetMapping("/{numero}")
    @ApiOperation("Consulta pelo Número da Conta Corrente")
    public ResponseEntity<ContaCorrenteDTO> buscarPorId(
            @ApiParam("Código da Conta Corrente")
            @PathVariable Long numero) {

        ContaCorrenteDTO contaCorrenteDTO =
                this.mapper.map(this.contaCorrenteService.buscarPorId(numero),
                        ContaCorrenteDTO.class);

        return ResponseEntity.ok(contaCorrenteDTO);
    }


    @PostMapping
    @ApiOperation("Criar uma Conta Corrente")
    public ResponseEntity<ContaCorrenteDTO> criar(
            @Valid @RequestBody ContaCorrenteDTO contaCorrenteDTO) {

        ContaCorrente contaCorrente = this.mapper.map(contaCorrenteDTO, ContaCorrente.class);

        this.contaCorrenteService.salvar(contaCorrente);

        URI location = URI.create(
                new StringBuffer()
                        .append("/contas/")
                        .append(contaCorrente.getConta())
                        .toString());

        return ResponseEntity.created(location).build();

    }

}
