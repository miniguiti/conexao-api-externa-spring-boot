package school.sptech.democonexaoapiexterna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoConexaoApiExternaApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoConexaoApiExternaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoConexaoApiExternaApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void mostrarLinkSwagger() {
		log.info("Swagger UI: http://localhost:8080/swagger-ui/index.html");
	}
}
