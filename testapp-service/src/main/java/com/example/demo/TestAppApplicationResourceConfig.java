package com.example.demo;

import com.example.demo.resource.Hello2Resource;
import com.example.demo.resource.HelloResource;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/testapp")
public class TestAppApplicationResourceConfig extends ResourceConfig {
	@PostConstruct
	public void init() {
		register(HelloResource.class);
	}
}
