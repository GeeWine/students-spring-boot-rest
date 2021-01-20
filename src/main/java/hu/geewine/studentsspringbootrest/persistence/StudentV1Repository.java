package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentV1Repository extends JpaRepository<StudentV1, Long>, CustomStudentV1Repository, JpaSpecificationExecutor<StudentV1> {
}
