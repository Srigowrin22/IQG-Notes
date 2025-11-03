package training.iqgateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
	
	@RequestMapping("/")
	public String sayHi(){
		return "hey Mannn";
	}

}
