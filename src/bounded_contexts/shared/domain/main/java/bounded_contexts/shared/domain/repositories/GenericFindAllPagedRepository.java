package bounded_contexts.shared.domain.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericFindAllPagedRepository<T> {

    Page<T> findAll(Pageable pageable);
}
