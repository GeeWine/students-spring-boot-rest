package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV1;

public interface CustomStudentV1Repository {

    boolean findByProperties(StudentV1 studentV1);

}
