package training.iqgateway;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

 

@RestController
public class HomeController {
	
	@requestMapping("/hello")
	public String sayHello(){
		return "Welcome to the New Era of Developing Spring Boot Application";
	}
}
