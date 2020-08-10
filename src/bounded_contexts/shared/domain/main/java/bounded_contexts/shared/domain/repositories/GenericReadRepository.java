package bounded_contexts.shared.domain.repositories;

import bounded_contexts.shared.domain.ValueObject;

public interface GenericReadRepository<T, ID extends ValueObject> extends GenericFindSingleRepository<T, ID>,
        GenericFindAllPagedRepository<T>, GenericExistsRepository<ID> {

}
