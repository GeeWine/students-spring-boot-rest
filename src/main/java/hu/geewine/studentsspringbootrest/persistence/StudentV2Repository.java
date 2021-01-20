package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentV2Repository extends JpaRepository<StudentV2, Long>, CustomStudentV2Repository, JpaSpecificationExecutor<StudentV2> {
}
