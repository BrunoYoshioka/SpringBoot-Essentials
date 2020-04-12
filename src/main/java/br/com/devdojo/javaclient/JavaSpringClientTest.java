package br.com.devdojo.javaclient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/user/students")
                .basicAuthentication("bruno", "devdojo").build();
        //getForEntity() retorna o objeto encapsulado no ResponseEntity, esse possui umas informações a mais como reader por exemplo, incluindo a propria resposta que é o objeto que queremos
        //getForObject() retorna diretamente o objeto puro do jeito que quer trabalhar
        Student student = restTemplate.getForObject("/{id}", Student.class, 1);
        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 1);
        System.out.println("getForObject:");
        System.out.println(student);
        System.out.println("----------------------\n");
        System.out.println("getForEntity:");
        System.out.println(forEntity);
        System.out.println(forEntity.getBody()); //utilizar o estudante dentro do ResponseEntity
        /*System.out.println("----------------------\n");
        System.out.println("lista de estudantes, usando getForObject:");
        Student[] students = restTemplate.getForObject("/", Student[].class); // fazer chamada para metodo get q retorna a lista de estudantes, usando getForObject
        System.out.println(students);
        System.out.println("----------------------\n");
        System.out.println("exchange que retorna o ResponseEntity:");
        ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() {
                });
        System.out.println(exchange.getBody());*/

        System.out.println("----------------------\n");
        System.out.println("lista de estudantes por ordenação, usando getForObject:");
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        System.out.println(exchange);
    }
}
