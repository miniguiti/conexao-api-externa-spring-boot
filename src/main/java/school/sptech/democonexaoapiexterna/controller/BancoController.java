package school.sptech.democonexaoapiexterna.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import school.sptech.democonexaoapiexterna.dto.BancoApiExternaDto;
import school.sptech.democonexaoapiexterna.dto.BancoDto;
import java.util.List;

@RestController
@RequestMapping("/bancos")
@Tag(name = "Bancos")
public class BancoController {

    private static final Logger log = LoggerFactory.getLogger(BancoController.class);

    @GetMapping
    @Operation(summary = "Listar bancos", description = """
            # Listar todos os bancos utilizando uma API externa
            ---
            Retorna uma lista com todos os bancos disponíveis na API.
            """)
    @ApiResponse(responseCode = "200", description = "Lista de bancos")
    public ResponseEntity<List<BancoDto>> listBancos() {
        RestClient client = RestClient.builder()
                .baseUrl("https://brasilapi.com.br/api/")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri("/banks/v1")
                .retrieve()
                .body(String.class);

        log.info("Resposta da API: " + raw);

        List<BancoApiExternaDto> bancos = client.get()
                .uri("/banks/v1")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (bancos == null) {
            return ResponseEntity.noContent().build();
        }

        List<BancoDto> resposta =
                bancos.stream().map(item -> {
                    BancoDto bancoDto = new BancoDto();
                    bancoDto.setCodigo(item.getCodigo());
                    bancoDto.setIspb(item.getIspb());
                    bancoDto.setNome(item.getNome());
                    bancoDto.setNomeCompleto(item.getNomeCompleto());
                    return bancoDto;
                }).toList();

        return ResponseEntity.ok(resposta);
    }
}
