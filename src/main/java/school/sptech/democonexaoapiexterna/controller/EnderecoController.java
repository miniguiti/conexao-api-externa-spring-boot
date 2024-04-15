package school.sptech.democonexaoapiexterna.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import school.sptech.democonexaoapiexterna.dto.EnderecoApiExternaDto;
import school.sptech.democonexaoapiexterna.dto.EnderecoDto;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereço")
public class EnderecoController {

  private static final Logger log = LoggerFactory.getLogger(EnderecoController.class);

  @GetMapping
  @Operation(summary = "Buscar dados do endereço", description = """
      # Busca os dados de um endereço a partir do CEP utilizando uma API externa
      ---
      Retorna os dados de endereço retornados da API.
      """)
  @ApiResponse(responseCode = "200", description = "Dados de endereço")
  public ResponseEntity<EnderecoDto> buscarEndereco(@RequestParam String cep) {

    RestClient client = RestClient.builder()
        .baseUrl("https://viacep.com.br/ws/")
        .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
        .build();

    String raw = client.get()
        .uri(cep + "/json")
        .retrieve()
        .body(String.class);

    log.info("Resposta da API: " + raw);

    EnderecoApiExternaDto endereco = client.get()
        .uri(cep + "/json")
        .retrieve()
        .body(EnderecoApiExternaDto.class);

    if (endereco == null) {
      return ResponseEntity.noContent().build();
    }

    EnderecoDto resposta = new EnderecoDto();
    resposta.setBairro(endereco.getBairro());
    resposta.setCep(endereco.getCep());
    resposta.setCidade(endereco.getCidade());
    resposta.setEstado(endereco.getEstado());
    resposta.setRua(endereco.getRua());

    return ResponseEntity.ok(resposta);
  }
}
