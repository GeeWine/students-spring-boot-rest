package hu.geewine.studentsspringbootrest.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractStudentSpecificationBuilder<E> {

    private final List<SearchCriteria> params;

    public AbstractStudentSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public AbstractStudentSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<E> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(StudentV1Specification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
