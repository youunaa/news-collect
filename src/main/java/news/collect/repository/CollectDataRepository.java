package news.collect.repository;

import news.collect.data.model.CollectData;
import org.springframework.data.repository.CrudRepository;

public interface CollectDataRepository extends CrudRepository<CollectData, Long> {

}
