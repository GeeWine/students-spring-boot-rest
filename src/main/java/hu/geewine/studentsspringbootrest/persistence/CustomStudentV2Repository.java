package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV2;

public interface CustomStudentV2Repository {

    boolean findByProperties(StudentV2 studentV2);

}
