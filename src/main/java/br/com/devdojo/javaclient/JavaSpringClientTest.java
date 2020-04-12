package br.com.devdojo.javaclient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class JavaSpringClientTest {
    public static void main(String[] args) {
        //insert("", "");
        //findById( /*Informe o id*/ 111);
        //findAll();
        delete( /*Informe o id*/ 24);
        //update("", "", /*Informe o id*/ );
        //listarEstudantesPorOrdenação("/?sort=id,desc&sort=name,asc");
    }

    private static void insert(String nome, String email){
        Student studentPost = new Student();
        studentPost.setName(nome);
        studentPost.setEmail(email);
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println("Registrar Estudante:\n" + dao.save(studentPost));
        System.out.println("-----------------------------");
    }

    private static void findById(long id){
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println("Encontrar por id:\n" + dao.findById(id));
        System.out.println("-----------------------------");
    }

    private static void listarEstudantesPorOrdenação(String url_sort){
        JavaClientDAO dao = new JavaClientDAO();
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/user/students")
                .basicAuthentication("bruno", "devdojo").build();
        ResponseEntity<PageableResponse<Student>> exchange = restTemplate.exchange(url_sort, HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Student>>() {
                });
        System.out.println(exchange);
    }

    private static void findAll(){
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println("Listar todos:\n" + dao.listAll());
        System.out.println("-----------------------------");
    }

    private static void update(String nome, String email, long id){
        Student studentPost = new Student();
        studentPost.setName(nome);
        studentPost.setEmail(email);
        studentPost.setId(id);
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println("Registrar Estudante:\n" + dao.save(studentPost));
        System.out.println("-----------------------------");
    }

    private static void delete(long id){
        JavaClientDAO dao = new JavaClientDAO();
        dao.delete(id);
    }
}
