<h1 align="center">API para cadastro de Politicos e Partidos</h1><br>

![como-api-funciona](https://user-images.githubusercontent.com/98189208/179321131-656d79ff-555d-4ca6-a9b5-5ee5f99ae474.jpg)

## Índice

* [Descrição do Projeto](#descrição-do-projeto)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)

## Descrição do projeto
<p>Projeto feito para a quarta avalição de java e spring boot. Nela é feita uma API em Java com Spring boot para cadastrar partidos e seus associados,<br>
buscar o partido e os associados, atualizar e deletar.</p><br>

## Funcionalidades e Demonstração da Aplicação

### Cadastros:<br>

Aqui é feito o cadastro dos partidos onde temos os campos.

- POST - http://localhost:8080/partidos<br>

{<br>
    "nome" : "Nome do partido (String)",<br>
    "sigla" : "Sigla do Partido (String)",<br>
    "ideologia" : "Ideologia do Partido (String)",<br>
    "dataFundacao" : "Data de fundação (Date)"<br> 
}<br>

Aqui é feito o cadastro dos associados onde temos os campos.

- POST - http://localhost:8080/associados<br>

{<br>
    "nome" : "nome do associado (String)",<br>
    "cargoPolitico" : "cargo do associado (String)",<br>
    "dataNascimento" : "data de nascimento de um associado (Date)",<br>
    "sexo" : "sexo de um associado (String)"<br>
}<br>

Aqui é onde vinculamos um associado a um partido.

- POST - http://localhost:8080/associados/partidos<br>

{<br>
    "idAssociado" : "1",<br>
    "idPartido" : "1"<br>
}

### Buscas:<br>

Aqui fazemos a busca de todos os partidos já cadastrados.

- GET - http://localhost:8080/partidos<br>

Aqui fazemos a busca de partidos por ID.

- GET - http://localhost:8080/partidos/{id}

Aqui fazemos a busca por ideologia.

- GET - http://localhost:8080/partidos?ideologia={ideologia}

Aqui fazemos a busca por dos associados de um partido.

- GET - http://localhost:8080/partidos/{id}/associados

Aqui fazemos a busca de todos os associados já cadastrados.

- GET - http://localhost:8080/associados

Aqui fazemos a busca de associados por ID.

- GET - http://localhost:8080/associados/{id}

Aqui fazemos a busca por cargo politico.

- GET - http://localhost:8080/associados/?cargo={cargoPolitico}

Aqui fazemos a busca pelo nome.

- GET - http://localhost:8080/associados?sort=nome



### Atualizações:<br>

Aqui atualizamos um partido.

- PUT http://localhost:8080/partidos/{id}

{<br>
    "nome" : "Nome do partido (String)",<br>
    "sigla" : "Sigla do Partido (String)",<br>
    "ideologia" : "Ideologia do Partido (String)",<br>
    "dataFundacao" : "Data de fundação (Date)"<br> 
}<br>

Aqui atualizamos um partido.

- PUT http://localhost:8080/associados/{id}

{<br>
    "nome" : "Luiz Inácio Lula da Silva",<br>
    "cargoPolitico" : "Presidente",<br>
    "dataNascimento" : "27/10/1945",<br>
    "sexo" : "Masculino"<br>
}

### Exclusões:<br>

Aqui deletamos um partido.

DELETE - http://localhost:8080/partidos/{id}

Aqui deletamos um associado.

DELETE - http://localhost:8080/associado/{id}

Aqui desvinculamos um associado de um partido.

DELETE - http://localhost:8080/associados/{id}/partidos/{id}

### Acesso ao Projeto

https://github.com/oswaldo-dev/avaliacao-4
