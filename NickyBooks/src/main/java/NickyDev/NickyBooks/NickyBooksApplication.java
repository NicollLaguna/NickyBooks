package NickyDev.NickyBooks;

import NickyDev.NickyBooks.principal.Ventana;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NickyBooksApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(NickyBooksApplication.class, args);
		Ventana ventana = new Ventana();
	}

}
