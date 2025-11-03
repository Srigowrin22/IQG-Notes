package training.iqgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class ReactiveController {

	private final ReactiveService reactiveService;

	public ReactiveController(ReactiveService reactiveService) {
		this.reactiveService = reactiveService;
	}

	@GetMapping("/messages")
	public Flux<String> getMessages() {
		return reactiveService.getMessages();
	}
}
