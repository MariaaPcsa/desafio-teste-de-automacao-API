package com.maria.teste.utils;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

public class SwaggerReader {

    public static void main(String[] args) {

        OpenAPI openAPI = new OpenAPIV3Parser()
                .read("src/test/resources/swagger.json");

        if (openAPI != null) {

            System.out.println("✅ Swagger carregado com sucesso!");

            System.out.println("Título: "
                    + openAPI.getInfo().getTitle());

            System.out.println("Versão: "
                    + openAPI.getInfo().getVersion());

            System.out.println("Endpoints:");

            openAPI.getPaths().forEach((path, item) -> {
                System.out.println(path);
            });

        } else {

            System.out.println("❌ Erro ao carregar Swagger");

        }
    }
}
