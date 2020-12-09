package springPayoll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	CommandLineRunner initDatabase(FuncionarioRepository funcionarioRepository) {
		
		return args -> {
			log.info("Preloading " + funcionarioRepository.save(new Funcionario("Bilbo Baggins", "burglar")));
		    log.info("Preloading " + funcionarioRepository.save(new Funcionario("Frodo Baggins", "thief")));
		};
	}
}
