package hu.geewine.studentsspringbootrest;

import hu.geewine.studentsspringbootrest.filter.SearchCriteria;
import hu.geewine.studentsspringbootrest.filter.StudentV1Specification;
import hu.geewine.studentsspringbootrest.filter.StudentV2Specification;
import hu.geewine.studentsspringbootrest.model.StudentV1;
import hu.geewine.studentsspringbootrest.model.StudentV2;
import hu.geewine.studentsspringbootrest.persistence.StudentV1Repository;
import hu.geewine.studentsspringbootrest.persistence.StudentV2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback
@SpringBootTest
public class StudentSpringBootRestApplicationTests {

    @Autowired
    private StudentV1Repository studentV1Repository;

    @Autowired
    private StudentV2Repository studentV2Repository;

    private StudentV1 testOneV1;
    private StudentV1 testTwoV1;
    private StudentV1 testThreeV1;
    private StudentV2 testOneV2;
    private StudentV2 testTwoV2;
    private StudentV2 testThreeV2;

    @BeforeEach
    public void init() {
        testOneV1 = new StudentV1();
        testOneV1.setAge(21L);
        testOneV1.setName("Test One");
        studentV1Repository.save(testOneV1);
        testTwoV1 = new StudentV1();
        testTwoV1.setAge(24L);
        testTwoV1.setName("Test Two");
        studentV1Repository.save(testTwoV1);
        testThreeV1 = new StudentV1();
        testThreeV1.setAge(22L);
        testThreeV1.setName("Three");
        studentV1Repository.save(testThreeV1);

        testOneV2 = new StudentV2();
        testOneV2.setAge(21L);
        testOneV2.setFirstName("Bill");
        testOneV2.setLastName("Doe");
        studentV2Repository.save(testOneV2);
        testTwoV2 = new StudentV2();
        testTwoV2.setAge(24L);
        testTwoV2.setFirstName("Will");
        testTwoV2.setLastName("Doe");
        studentV2Repository.save(testTwoV2);
        testThreeV2 = new StudentV2();
        testThreeV2.setAge(22L);
        testThreeV2.setFirstName("Jill");
        testThreeV2.setLastName("Doe");
        studentV2Repository.save(testThreeV2);
    }

    @Test
    void filterAgeV1() {
        StudentV1Specification specification = new StudentV1Specification((new SearchCriteria("age", ":", 21)));

        List<StudentV1> students = studentV1Repository.findAll(specification);

        assertThat(testOneV1).isIn(students);
        assertThat(testTwoV1).isNotIn(students);
        assertThat(testThreeV1).isNotIn(students);
    }

    @Test
    void filterNameV1() {
        StudentV1Specification specification = new StudentV1Specification((new SearchCriteria("name", ":", "Test")));

        List<StudentV1> students = studentV1Repository.findAll(specification);

        assertThat(testOneV1).isIn(students);
        assertThat(testTwoV1).isIn(students);
        assertThat(testThreeV1).isNotIn(students);
    }
    

    @Test
    void filterAgeV2() {
        StudentV2Specification specification = new StudentV2Specification((new SearchCriteria("age", ":", 21)));

        List<StudentV2> students = studentV2Repository.findAll(specification);

        assertThat(testOneV2).isIn(students);
        assertThat(testTwoV2).isNotIn(students);
        assertThat(testThreeV2).isNotIn(students);
    }

    @Test
    void filterFirstNameV2() {
        StudentV2Specification specification = new StudentV2Specification((new SearchCriteria("firstName", ":", "Will")));

        List<StudentV2> students = studentV2Repository.findAll(specification);

        assertThat(testOneV2).isNotIn(students);
        assertThat(testTwoV2).isIn(students);
        assertThat(testThreeV2).isNotIn(students);
    }

    @Test
    void filterLastNameV2() {
        StudentV2Specification specification = new StudentV2Specification((new SearchCriteria("lastName", ":", "Doe")));

        List<StudentV2> students = studentV2Repository.findAll(specification);

        assertThat(testOneV2).isIn(students);
        assertThat(testTwoV2).isIn(students);
        assertThat(testThreeV2).isIn(students);
    }

}
