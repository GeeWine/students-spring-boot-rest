package hu.geewine.studentsspringbootrest.service;

import hu.geewine.studentsspringbootrest.filter.StudentV2SpecificationsBuilder;
import hu.geewine.studentsspringbootrest.model.StudentV2;
import hu.geewine.studentsspringbootrest.persistence.StudentV2Repository;
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
public class StudentV2Service {

    @Autowired
    private StudentV2Repository studentV2Repository;

    public List<StudentV2> findAllV2() {
        return studentV2Repository.findAll();
    }

    public List<StudentV2> searchStudentsV2(String search) {
        StudentV2SpecificationsBuilder builder = new StudentV2SpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<StudentV2> spec = builder.build();
        return studentV2Repository.findAll(spec);
    }

    public StudentV2 findStudentV2ById(long id) {
        Optional<StudentV2> student = studentV2Repository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        return student.get();
    }

    public StudentV2 saveV2(StudentV2 studentV2) {
        boolean unique = studentV2Repository.findByProperties(studentV2);
        if (unique) {
            return studentV2Repository.save(studentV2);
        } else {
            throw new StudentDuplicationException();
        }
    }

    public void updateStudentV2(StudentV2 studentV2, long id) {
        Optional<StudentV2> storedStudent = studentV2Repository.findById(id);
        if (!storedStudent.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        studentV2.setId(id);
        saveV2(studentV2);
    }

    public void deleteStudentV2ById(long id) {
        studentV2Repository.deleteById(id);
    }

}
