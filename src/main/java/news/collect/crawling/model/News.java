package news.collect.crawling.model;

import lombok.*;
import news.collect.repository.BaseTimeEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "News")
public class News extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type; // 뉴스 타입(네이버 or 다음)

    @Column(name = "keyword")
    private String keyword; // 뉴스 keyword

    @Column(name = "subject")
    private String subject; // 뉴스 제목

    @Column(name = "newsUrl")
    private String newsUrl; // 뉴스 url

    @Column(name = "collectDt", nullable = true)
    private Date collectDt; // 데이터 수집일

    @Override
    public String toString() {
        return "News{" +
                "subject='" + subject + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                '}';
    }

}