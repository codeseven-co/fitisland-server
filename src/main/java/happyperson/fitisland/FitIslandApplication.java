package happyperson.fitisland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FitIslandApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitIslandApplication.class, args);
	}

}
