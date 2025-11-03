package training.iqgateway;

import java.time.Duration;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ReactiveService {
	public Flux<String> getMessages() {
		return Flux.just("Hello ", "Welcome ", "To New Era ", "Of ", "Reactive Pogramming ")
				.delayElements(Duration.ofSeconds(5));
	}
}
