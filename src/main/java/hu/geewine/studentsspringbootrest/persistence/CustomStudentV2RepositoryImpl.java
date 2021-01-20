package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.StudentV2;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentV2RepositoryImpl implements CustomStudentV2Repository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean findByProperties(StudentV2 studentV2) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("from StudentV2 where 1=1 ");

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasLength(studentV2.getFirstName())) {
            jpql.append("and firstName = :firstName ");
            parameters.put("firstName", studentV2.getFirstName());
        }

        if (StringUtils.hasLength(studentV2.getLastName())) {
            jpql.append("and lastName = :lastName ");
            parameters.put("lastName", studentV2.getLastName());
        }

        if (studentV2.getAge() != null) {
            jpql.append("and age = :age ");
            parameters.put("age", studentV2.getAge());
        }

        TypedQuery<StudentV2> query = entityManager.createQuery(jpql.toString(), StudentV2.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        List<StudentV2> results = query.getResultList();

        return results.size() > 0 ? false : true;
    }

}
