package hu.geewine.studentsspringbootrest.service;

import hu.geewine.studentsspringbootrest.filter.StudentV1SpecificationsBuilder;
import hu.geewine.studentsspringbootrest.model.StudentV1;
import hu.geewine.studentsspringbootrest.persistence.StudentV1Repository;
import hu.geewine.studentsspringbootrest.util.StudentDuplicationException;
import hu.geewine.studentsspringbootrest.util.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentV1Service {

    @Autowired
    private StudentV1Repository studentV1Repository;

    public List<StudentV1> findAllV1() {
        return studentV1Repository.findAll();
    }

    public List<StudentV1> searchStudentsV1(String search) {
        StudentV1SpecificationsBuilder builder = new StudentV1SpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<StudentV1> spec = builder.build();
        return studentV1Repository.findAll(spec);
    }

    public StudentV1 findStudentV1ById(long id) {
        Optional<StudentV1> student = studentV1Repository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        return student.get();
    }

    public StudentV1 saveV1(StudentV1 studentV1) {
        boolean unique = studentV1Repository.findByProperties(studentV1);
        if (unique) {
            return studentV1Repository.save(studentV1);
        } else {
            throw new StudentDuplicationException();
        }
    }

    public void updateStudentV1(StudentV1 studentV1, long id) {
        Optional<StudentV1> storedStudent = studentV1Repository.findById(id);
        if (!storedStudent.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        studentV1.setId(id);
        saveV1(studentV1);
    }

    public void deleteStudentV1ById(long id) {
        studentV1Repository.deleteById(id);
    }

}
