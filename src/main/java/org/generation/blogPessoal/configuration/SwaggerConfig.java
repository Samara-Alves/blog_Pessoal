package org.generation.blogPessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Projeto Blog Pessoal - Generation Brasil")
						.version("v0.0.1")
						.license(new License().name("Generation Brazil").url("https://brazil.generation.org/"))
						.contact(new Contact()
								.name("Conteudo Generation").url("https://github.com/Samara-Alves")
								.email("samara_alves28@hotmail.com")))
				.externalDocs(new ExternalDocumentation().description("Github")
						.url("<https://github.com/Samara-Alves/blog_Pessoal>"));
	}
			private ApiResponse createApiResponse(String message) {
				return new ApiResponse().description(message);
		}
	

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses api = operation.getResponses();

				api.addApiResponse("200", createApiResponse("Sucesso!"));
				api.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				api.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				api.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				api.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				api.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

}