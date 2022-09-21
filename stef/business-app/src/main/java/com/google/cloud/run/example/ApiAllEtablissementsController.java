package com.google.cloud.run.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;

@Controller("/etablissements") 
public class ApiAllEtablissementsController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApiAllEtablissementsController.class);
    
    private final HttpClient httpClient;

    public ApiAllEtablissementsController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /*
    @Get(produces = MediaType.APPLICATION_JSON) 
    public String index() {
        String uri = "https://api.stef.com/stef/quaext/referentiel-structure/v1/etablissements";
        try {
            LOG.info("Calling API at " + uri + "...");
            String body = HttpClient.newHttpClient().send(HttpRequest.newBuilder()
                            .uri(URI.create(uri))
                            .GET()
                            .header("X-STEF-Client-Id", Application.secretApiToken)
                            .build(), HttpResponse.BodyHandlers.ofString())
                            .body();
            return body;
        } catch(Throwable t) {
            LOG.error("Error when accessing the Etablissements API", t);
            return "Error when accessing the Etablissements API :(";
        }
    }
     */

    @Get(produces = MediaType.APPLICATION_JSON) 
    public HttpResponse<?> index() {
        try {
            return Application.fetchAllEtablissements(this.httpClient);
        }catch(IllegalStateException e) {
            return HttpResponse.unauthorized().body(e.getMessage());
        }
    }
}