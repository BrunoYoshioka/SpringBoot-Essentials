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

        Student studentPost = new Student();
        studentPost.setName("John Wick");
        studentPost.setEmail("john.wick@pencil.com");
        JavaClientDAO dao = new JavaClientDAO();
        System.out.println("Encontrar por id:\n" + dao.findById(10));
        System.out.println("-----------------------------");
        System.out.println("Listar todos:\n" + dao.listAll());
        System.out.println("-----------------------------");
        System.out.println("Registrar Estudante:\n" + dao.save(studentPost));
        System.out.println("-----------------------------");
        List<Student> students = dao.listAll();
        System.out.println("Contar Estudantes:\n" + students);
        System.out.println("-----------------------------");
    }
}
