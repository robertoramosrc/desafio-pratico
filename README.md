#Desafio prático

#Considerações

- O que vale ser destacado:
    - Utilização de Stream para conversão de dados, entre DTOs e objetos da camada de Service
    - Respeito a arquitetura por camadas
    - Tratamento de exceções a nivel das APIs via Handler
    - Além dos Testes unitários , foram realizados testes de Contexto e End to End  

- O que poderia ser melhorado na próxima versão:
    - Persistência das transferências realizadas, para consultas de acompanhamento
    - Agendamento de Transferências em data futura 
    - Criar a possibilidade de extorno de lançamentos


#Escopo do Desafio
Nesse desafio, você desenvolverá um serviço que faz parte de uma API bancária.
Para o escopo do desafio, você deverá criar um serviço que realize uma operação de transferência entre contas correntes. 
A operação de transferência é realizada informando-se uma conta de débito (origem), uma conta de crédito (destino) e o valor a ser transferido.

A solução deve conter as entidades: conta corrente e lançamento. (Você pode incrementar com outras entidades se achar necessário)
O que será avaliado:

  •	As funcionalidades do serviço devem ser expostas em uma API REST.
  
  •	Critérios de arquitetura como separação de responsabilidade e clean code.
  
  •	A implementação deve conter um nível aceitável de testes automatizados.
  
  •	Você não precisa realizar a persistência de dados em um servidor. S
  e preferir, pode utilizar uma base de dados in-memory como H2, por exemplo.
  
  •	Certifique-se de incluir um arquivo readme na raiz da solução com as informações relevantes para rodar seu projeto, incluindo:
  
    o O que vale destacar no código implementado?
    
    o O que poderia ter sido feito para melhorar o código desenvolvido? O que você faria em uma versão 2.0?
    
    o Qualquer outra informação que você julgue relevante para a avaliação do seu teste.
    
  •	Publique o código em um repositório Git público, como Github ou Bitbucket, por exemplo, e mande para nós.
  
