package com.mangesh.springweb.healthchecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
@Component
public class CustomHealthIndicator implements HealthIndicator {

	@Override
	public Health health() {
		boolean error=true;
		if(error) {
			
			return Health.down().withDetail("Error key",123).build();
			
		}
		return Health.up().build();
	}

}
