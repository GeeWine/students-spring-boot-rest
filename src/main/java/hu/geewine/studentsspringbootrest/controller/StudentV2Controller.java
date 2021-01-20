package hu.geewine.studentsspringbootrest.controller;

import hu.geewine.studentsspringbootrest.model.StudentV2;
import hu.geewine.studentsspringbootrest.service.StudentV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v2/students")
public class StudentV2Controller {

    @Autowired
    private StudentV2Service studentV2Service;

    @GetMapping(value = "")
    public List<StudentV2> retrieveAllStudentsV2() {
        return studentV2Service.findAllV2();
    }

    @GetMapping(value = "/filter", produces = "application/vnd.gw.students.rest-v2+json")
    public List<StudentV2> searchStudentsV2(@RequestParam(value = "search") String search) {
        return studentV2Service.searchStudentsV2(search);
    }

    @GetMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v2+json")
    public StudentV2 findStudentV2ById(@PathVariable long id) {
        return studentV2Service.findStudentV2ById(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createStudent(@RequestBody StudentV2 studentV2) {
        StudentV2 savedStudent = studentV2Service.saveV2(studentV2);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v2+json")
    public ResponseEntity<Object> updateStudentV2(@RequestBody StudentV2 studentV2, @PathVariable long id) {
        studentV2Service.updateStudentV2(studentV2, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v2+json")
    public void deleteStudentV2ById(@PathVariable long id) {
        studentV2Service.deleteStudentV2ById(id);
    }

}
