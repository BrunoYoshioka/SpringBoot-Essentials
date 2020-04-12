package br.com.devdojo.javaclient;

import br.com.devdojo.handler.RestResponseExceptionHandler;
import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaClientDAO {
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/user/students")
            .basicAuthentication("bruno", "devdojo")
            .errorHandler(new RestResponseExceptionHandler())
            .build();
    private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
            .rootUri("http://localhost:8080/v1/admin/students")
            .basicAuthentication("bruno", "devdojo")
            .errorHandler(new RestResponseExceptionHandler())
            .build();
    //getForEntity() retorna o objeto encapsulado no ResponseEntity, esse possui umas informações a mais como reader por exemplo, incluindo a propria resposta que é o objeto que queremos
    //getForObject() retorna diretamente o objeto puro do jeito que quer trabalhar
    public Student findById(long id){
        return restTemplate.getForObject("/{id}", Student.class, id);
    }

    public List<Student> listAll(){
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {});
        return exchange.getBody().getContent();
    }

    public Student save(Student student){
        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("/",
                HttpMethod.POST, new HttpEntity<>(student, createJSONHeader()), Student.class);
        return exchangePost.getBody();
    }

    public void update(Student student){
        restTemplateAdmin.put("/", student);
    }

    public void delete(long id){
        restTemplateAdmin.delete("/{id}", id);
    }

    private static HttpHeaders createJSONHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
