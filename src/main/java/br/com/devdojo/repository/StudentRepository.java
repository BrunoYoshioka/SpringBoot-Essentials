package br.com.devdojo.repository;

import br.com.devdojo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    //List<Student> findByName(String name); // Encontrar por nome = no banco
    List<Student> findByNameIgnoreCaseContaining(String name); // Encontrar por nome em partes
}
