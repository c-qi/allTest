package org.zhire.work.es;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Repository
 * @author
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends CrudRepository<T, ID> {

}
