
# TrackMe

APIRest que permite cadastrar, buscar, atualizar e deletar um funcionário.


## 🛠 Tecnologias utilizadas

- Java 17
- Maven
- Springboot
- Spring Data JPA
- H2 Database
- Swagger
- PostgreSQL
- Junit/Mockito
- Github actions

## 🔗 Links
- API URL: [http://teste-tracknme.herokuapp.com/funcionarios](http://teste-tracknme.herokuapp.com/funcionarios)
- SWAGGER DOCUMENTATION: [http://teste-tracknme.herokuapp.com/swagger-ui/index.html#/](http://teste-tracknme.herokuapp.com/swagger-ui/index.html#/)

## 💻 Funcionalidades
- Integração total com o Spring Framework mais recente para o desenvolvimento da API.
- Um banco de dados na memória (H2) para armazenar os dados para fins de testes.
- Testes unitários com Junit/Mockito.
- Heroku Cloud para fazer deploy da API.
- CI/CD utilizando Github actions para compilar, testar e fazer deploy na cloud(HEROKU) sempre que houver alteração na branch master.
- RestTemplate para consumir a API externa(Viacep) e utilizar seus dados.
- Swagger para "autodocumentar" toda API.




## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/lucasfroque/TracknMe-teste
```

Entre no diretório do projeto

```bash
  cd TracknMe-teste
```

Instale as dependências

```bash
  mvn clean install
```

Inicie o servidor

```bash
  mvn spring-boot:run
```


## Rodando os testes

Para rodar os testes, rode o seguinte comando

```bash
  mvn test
```


## Documentação da API

#### Cria um novo funcionario

```http
  POST /funcionarios
```

#### Retorna todos os funcionarios

```http
  GET /funcionarios
```

#### Retorna um funcionario

```http
  GET /funcionarios/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do funcionario que você quer |

#### Retorna todos os funcionarios com o CEP específico

```http
  GET /funcionarios/cep/${cep}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O CEP que você quer |

#### Atualiza um funcionario

```http
  PUT /funcionarios/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do funcionario que você quer |

#### Deleta um funcionario

```http
  DELETE /funcionarios/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do funcionario que você quer |
