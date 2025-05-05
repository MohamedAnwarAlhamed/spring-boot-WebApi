package ProductApi.ProductManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProductManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductManagementApplication.class, args);
	}

}
