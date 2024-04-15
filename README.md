![readme](https://github.com/miniguiti/conexao-api-externa-spring-boot/assets/37668247/41f8321d-1e05-47cf-9700-88a24890c77d)
# Exemplo de conexão com API externa utilizando o RestClient do Spring

## ⚙️ Configuração de Ambiente: 
### Passo a passo execução:
1. Clonar Repositório
2. Abrir projeto no IntelliJ ou sua IDE de preferencia
3. Executar projeto na IDE

### API Documentada com Swagger:
Para acessar os endpoints desenvolvidos, após rodar o projeto em sua maquina e acesse o endereço: http://localhost:8080/swagger-ui/index.html

#
## 🔌 ```EnderecoController```: 
Busca dados de um endereço a partir do CEP utilizando uma API externa

O código abaixo explica a classe ```EnderecoController```, analise a classe antes para assimilar melhor a explicação: 

## 1) Criando o Client:
### Padrão de Projeto Builder:
Para entender o padrão de projeto builder: https://refactoring.guru/pt-br/design-patterns/builder

No builder podemos especificar algumas configurações básicas para criar o nosso RestClient: 


- ```baseUrl```: é o caminho principal da API que queremos acessar, uma API pode ter diversos endpoints que podemos utilizar e o baseUrl é o ponto de partida de todos esses caminhos.

- ```httpMessageConverters```: aqui especificamos quais classes serão responsáveis por converter as mensagens enviadas e recebidas nos formatos corretos. Iremos utilizar o Jackson para realizar a conversão de Java para JSON e JSON para Java.

- Trecho completo de criação do client:
  	```
    RestClient client = RestClient.builder()
        .baseUrl("https://viacep.com.br/ws/")
        .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
        .build();
   ```

## 2) Requisição: 
### Fazendo uma requisição GET que devolve as informações de Bancos 
- ```uri```: Caminho do endpoint a partir da url base
- ```retrieve```: Devolve um objeto de resposta
- ```body```: Devolve o corpo da resposta no formato especificado
- Trecho completo da requisição:
   ```
    String raw = client.get()
        .uri(cep + "/json")
        .retrieve()
        .body(String.class);

    log.info("Resposta da API: " + raw);
    ```

### Usando a reposta da requisição
Agora que sabemos o formato de resposta da API podemos criar um objeto Java para mapear atributos que iremos utilizar.
Podemos utilizar anotações do Jackson como ```@JsonProperty``` para mapear atributos com nomes diferentes dos utilizados na API externa. Exemplo:
- ```uf``` -> ```estado```
- ```localidade``` -> ```cidade``` 
- ```logradouro``` -> ```rua```

Veja o restante do mapeamento na classe ```EnderecoApiExternaDto```
- Trecho completo usando a resposta requisição:
  ```
    EnderecoApiExternaDto endereco = client.get()
        .uri(cep + "/json")
        .retrieve()
        .body(EnderecoApiExternaDto.class);

    if (endereco == null) {
      return ResponseEntity.noContent().build();
    }
  ```
### Mapeamento
Vamos mapear a resposta da API para um formato de dados que melhor se enquadra no contexto da nossa API.
```
   EnderecoDto resposta = new EnderecoDto();
    resposta.setBairro(endereco.getBairro());
    resposta.setCep(endereco.getCep());
    resposta.setCidade(endereco.getCidade());
    resposta.setEstado(endereco.getEstado());
    resposta.setRua(endereco.getRua());

    return ResponseEntity.ok(resposta);
```

#
## 🔌 ```BancoController```: Exemplo de consumo de API com ordenação
Lista todos os bancos utilizando uma API externa

A requisição feita na classe ```BancoController``` utiliza os mesmo recursos explicados no exemplo acima (EnderecoController), porém no exemplo abaixo guardamos o retorno da requisição em uma lista, que pode ser ordenada posteriormente.


## Autores:
👦🏻 @manoelalmeida-io
👩🏻 @miniguiti
