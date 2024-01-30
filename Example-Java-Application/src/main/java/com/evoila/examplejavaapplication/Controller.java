package com.evoila.examplejavaapplication;

import com.evoila.examplejavaapplication.util.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class Controller {
    int result;
    private Calculator calculator;

    @Autowired
    public Controller(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * This is an example documentation
     @return A simple message String
     */
    @GetMapping("/")
    public String index() {
        return "Dies ist eine Beispielanwendung, die API findet sich unter dem Endpoint /swagger-ui.html bzw. /v3/api-docs";
    }

    @GetMapping("/add")
    public String addition(@RequestParam(defaultValue = "0") Integer first, @RequestParam(defaultValue = "0") Integer second) {
        result = calculator.add(first, second);
        return "Result is: " + result;
    }

    @GetMapping("/lastResult")
    public String lastResult() {
        return "Last result was: " + result;
    }

    @GetMapping("/getPythonServer")
    public String getPythonServer() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(System.getenv("SERVER_URL")))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return "The Response-Code: " + response.statusCode() + " Response: " + response.body();
        //return null;
    }
}