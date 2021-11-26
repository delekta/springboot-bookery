package pl.delekta.bookery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookeryOnlineStoreApp {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BookeryOnlineStoreApp.class, args);
//		System.out.println(context);
	}

}
