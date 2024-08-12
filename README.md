# API - Sistema D.E.V.S

Atividade de estudos feita em Java com Spring Boot. 
Consiste em um sistema fictício de engenharia de proteção: D.E.V.S. (Dispositivo Eletrônico de Vigilância e
Segurança), nele poderá ser feito o cadastro de mutantes e gerenciamento de inimigos derrotados e da segurança da escola.

### Conhecimentos Aplicados 
- Criação de API com Spring Boot
- Relacionamento entre entidades
- Autenticação com Spring Security, JWT Tokens e Roles

## API Endpoints
O sistema contém os seguintes endpoints:

```markdown
POST /auth/login - Faz o login e recebe no retorno o token JWT.

POST /auth/register - Registra um novo usuário/mutante.


GET /mutant - Retorna todos os mutantes cadastrados.

GET /mutant/{id} - Retorna o mutante de id específico.

GET /mutant/inside-school - Retorna todos os mutantes que estão na escola.

GET /mutant/{id}/check-recruitment - Retorna o status de recrutamento do programa E.S.P.A.D.A.

POST /mutant - Registra um novo mutante.

PUT /mutant/{id} - Altera um mutante existente.

DELETE /mutant/{id} - Deleta um mutante.


GET /defeated-enemies - Retorna todos os registros de inimigos derrotados.

GET /defeated-enemies/{mutantId} - Retorna todos os registros de inimigos derrotados por um mutante específico.

POST /defeated-enemies - Insere um novo registro de inimigos derrotados.


GET /school-entries - Retorna todos os registros de entrada e saída da escola.

GET /school-entries/count-entries - Retorna o número de mutantes dentro da escola.

POST /entry/mutant/{mutantId} - Faz o registro de entrada do mutante.

POST /exit/mutant/{mutantId} - Faz o registro de saída do mutante.
```

## Autenticação

A API usa Spring Security para controle de autenticação.
Para acessar os endpoints protegidos, é necessário fornecer as credenciais de autenticação retornadas ao fazer o login (Bearer Token) no cabeçalho das requisições.