package training.iqgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import training.iqgateway.service.AdminService;

@SpringBootApplication
public class TMSSpringbootJPA{
	
	
	@Autowired(required = true)
	private AdminService adminServiceRef;
	
	public static void main(String[] args) {
		SpringApplication.run(TMSSpringbootJPA.class, args);
	}	
}


