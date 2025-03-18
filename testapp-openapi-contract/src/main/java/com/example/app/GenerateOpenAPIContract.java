package com.example.app;

import com.example.demo.TestAppApplicationResourceConfig;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.api.OpenApiContext;
import io.swagger.v3.oas.models.OpenAPI;

import java.io.File;
import java.io.FileOutputStream;

public class GenerateOpenAPIContract {
	public static void main(String [] args) throws Exception {
		OpenApiContext ctx = new JaxrsOpenApiContextBuilder<>()
				.application(new TestAppApplicationResourceConfig())
				.buildContext(true);
		OpenAPI oas = ctx.read();
		File classes = new File("target", "classes");
		File openapiContracts = new File(classes, "openapi-contracts");
		if (!openapiContracts.exists() && !openapiContracts.mkdirs()) {
			System.err.println("Could not mkdir: " + openapiContracts);
			System.exit(1);
		}
		File yamlFile = new File(openapiContracts, "testapp.yaml");
		try (FileOutputStream fileOutputStream = new FileOutputStream(yamlFile)) {
			ctx.getOutputYamlMapper()
					.writer(new DefaultPrettyPrinter())
					.writeValue(fileOutputStream, oas);
		}
		System.out.println("Created: " + yamlFile.getCanonicalPath());
	}
}
