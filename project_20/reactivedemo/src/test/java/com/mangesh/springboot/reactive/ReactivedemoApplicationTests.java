package com.mangesh.springboot.reactive;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class ReactivedemoApplicationTests {

	@Test
	void testMono() {
		Mono<String> mono = Mono.just("Macbook Pro");

		mono.log().map(data -> data.toUpperCase()).subscribe(System.out::println);
		// .subscribe(data->System.out.println(data));
	}

	@Test
	void testFlux() throws InterruptedException {
		// Flux.just("Macbook Pro","IPhone","Dell")
		// Flux.fromIterable(Arrays.asList("Macbook Pro","IPhone","Dell"))
		// .delayElements(Duration.ofSeconds(2))
		Flux.fromIterable(Arrays.asList("Macbook Pro", "IPhone", "Dell", "Macbook Pro", "IPhone", "Dell", "Macbook Pro",
				"IPhone", "Dell")).log().map(data -> data.toUpperCase()).subscribe(new Subscriber<String>() {

					private long count = 0;
					private Subscription subscription;

					@Override
					public void onSubscribe(Subscription subscription) {
						this.subscription = subscription;
						subscription.request(3);

					}

					@Override
					public void onNext(String order) {
						count++;
						if (count >= 3) {
							count = 0;
							subscription.request(3);
						}
						System.out.println(order);
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onComplete() {
						System.out.println("Completely Done!!");
					}
				});
		// .subscribe(new OrderConsumer());
		// .subscribe(data->System.out.println(data));
		Thread.sleep(6000);
	}

}
