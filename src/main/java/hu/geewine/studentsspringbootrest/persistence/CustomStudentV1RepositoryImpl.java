package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV1;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentV1RepositoryImpl implements CustomStudentV1Repository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean findByProperties(StudentV1 studentV1) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("from StudentV1 where 1=1 ");

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasLength(studentV1.getName())) {
            jpql.append("and name = :name ");
            parameters.put("name", studentV1.getName());
        }

        if (studentV1.getAge() != null) {
            jpql.append("and age = :age ");
            parameters.put("age", studentV1.getAge());
        }

        TypedQuery<StudentV1> query = entityManager.createQuery(jpql.toString(), StudentV1.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        List<StudentV1> results = query.getResultList();

        return results.size() > 0 ? false : true;
    }

}
