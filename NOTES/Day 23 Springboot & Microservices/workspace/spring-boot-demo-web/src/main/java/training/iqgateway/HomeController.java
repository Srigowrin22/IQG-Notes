package training.iqgateway;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class HomeController {
	
	@RequestMapping("/hello")
	public String sayHello(){
		return "Welcome to new era of developing web apps ";
	}
 
}