package com.google.cloud.run.example;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.core.type.Argument;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/names") 
public class ApiAllNamesController {
    
    private static final Logger LOG = LoggerFactory.getLogger(ApiAllNamesController.class);
    
    private final HttpClient httpClient;

    public ApiAllNamesController(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Get(produces = MediaType.APPLICATION_JSON) 
    public HttpResponse<?> index() {
        try {
            HttpResponse<List<Etablissement>> resp = Application.fetchAllEtablissements(this.httpClient);
            List<Etablissement> etabs = resp.body();
            LOG.info( etabs.size() + " etablissements fetched");
            // Don't send the full data, keep only the Etablissement stubs!
            List<Etablissement.Stub> stubs = etabs.stream().map(
                etab -> etab.getStub()
            ).collect(Collectors.toList());
            return HttpResponse.created(stubs);
        }catch(IllegalStateException e) {
            return HttpResponse.unauthorized().body(e.getMessage());
        }
    }

}