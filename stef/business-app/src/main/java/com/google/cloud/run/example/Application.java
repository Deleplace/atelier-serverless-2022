package com.google.cloud.run.example;

import io.micronaut.runtime.Micronaut;
import java.util.List;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.core.type.Argument;

public class Application {
    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    static final String stefApiEndpoint = "https://api.stef.com/stef/quaext/referentiel-structure/v1/etablissements";

    static HttpResponse<List<Etablissement>> fetchAllEtablissements(HttpClient httpClient)  {
        // TODO prefer reading this from Secret Manager instead
        String token = System.getenv("ETAB_SECRET_TOKEN");
        if(token==null || token.isBlank())
            throw new IllegalStateException("No Etablissement API TOKEN is configured");

        HttpRequest<?> request = HttpRequest.GET(stefApiEndpoint)
            .header("X-STEF-Client-Id", token);
        return httpClient.toBlocking().exchange(request, Argument.listOf(Etablissement.class));
    }
}
