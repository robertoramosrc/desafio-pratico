package br.com.dbserver.desafiopratico.config;

import br.com.dbserver.desafiopratico.dto.LancamentoDTO;
import br.com.dbserver.desafiopratico.model.Lancamento;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMappingConfig {

    @Bean
    public ModelMapper getModelMapping(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Lancamento.class, LancamentoDTO.class)
                .addMapping((a) -> a.getConta().getConta(), LancamentoDTO::setConta)
                .addMapping((a) -> a.getConta().getAgencia(), LancamentoDTO::setAgencia)
                .addMapping((a) -> a.getConta().getCorrentista(), LancamentoDTO::setCorrentista)  ;

        return modelMapper;

    }

}
