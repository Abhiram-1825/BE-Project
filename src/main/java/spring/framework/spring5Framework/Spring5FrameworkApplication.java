package spring.framework.spring5Framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.framework.spring5Framework.repositories.SupplierRepository;

@SpringBootApplication
public class Spring5FrameworkApplication {

	public static void main(String[] args) {
			SpringApplication.run(Spring5FrameworkApplication.class, args);
		}
}
