package com.google.cloud.run.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.core.type.Argument;
import java.util.List;
import java.time.Duration;

@Controller("/detail/{code}") 
public class ApiDetailController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApiDetailController.class);
    
    private final HttpClient httpClient;

    public ApiDetailController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Etablissement> index(String code) {
        LOG.info("Detail for " + code);

        HttpResponse<List<Etablissement>> resp = Application.fetchAllEtablissements(this.httpClient);
        LOG.info( resp.body().size() + " etablissements fetched");


        // In the full list, find the Etablissement matching the provided code e.g. "H86"
        for(Etablissement detail: resp.body())
            if(code.equals(detail.code))
                return HttpResponse.created(detail);

        // Not found => 404
        LOG.info("No Etablissement found for " + code + " :(");
        return null;
    }
}