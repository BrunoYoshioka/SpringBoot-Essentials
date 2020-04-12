package br.com.devdojo.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@ControllerAdvice // Configuração global
public class RestResponseExceptionHandler extends DefaultResponseErrorHandler {
    // Sobrescrita dos métodos
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Inside hasError"); //Dentro do hasError
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Doing something with status code " + response.getRawStatusCode()); //Fazendo algo com código de status
        System.out.println("Doing something with body " + IOUtils.toString(response.getBody(), "UTF-8")); //Fazendo algo com corpo da requisição
        //super.handleError(response);
    }
}
