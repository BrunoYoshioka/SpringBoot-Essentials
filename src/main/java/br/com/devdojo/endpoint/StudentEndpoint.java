package br.com.devdojo.endpoint;

import br.com.devdojo.error.ResourceNotFoundException;
import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentEndpoint {

    private final StudentRepository studentDAO;
    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    @GetMapping(path = "user/students")
    public ResponseEntity<?> listAll(Pageable pageable){
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    // Buscar um valor dessa lista
    @GetMapping(path = "user/students/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long id,
                                            @AuthenticationPrincipal UserDetails userDetails){
        /* Student student = studentDAO.findOne(id); // java 7
        if (student == null) // Caso não encontrado
            throw new ResourceNotFoundException("Student not found for ID: "+id);
        return new ResponseEntity<>(student, HttpStatus.OK);*/
        verifyIfStudentExists(id); // método verificar se o id estudante existe.
        Student student = studentDAO.findById(id).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(path = "user/students/findByName/{name}")
    public ResponseEntity<?> findStudentsByName(@PathVariable String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }

    @PostMapping(path = "admin/students")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "admin/students/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        /*studentDAO.delete(id); // java 7 */
        verifyIfStudentExists(id); // método verificar se o id estudante existe.
        studentDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "admin/students")
    public ResponseEntity<?> update(@RequestBody Student student){
        verifyIfStudentExists(student.getId()); // método verificar se o id estudante existe.
        studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void verifyIfStudentExists(Long id){
        Student student = studentDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found for ID: " +id));
    }
}
