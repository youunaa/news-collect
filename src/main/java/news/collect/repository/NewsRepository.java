package news.collect.repository;

import news.collect.crawling.model.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NewsRepository extends PagingAndSortingRepository<News, Long> {

    List<News> findAllByType(String userId, Pageable pageable);

}
