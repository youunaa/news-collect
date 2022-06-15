package news.collect.crawling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "News")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="type")
    private String type; // 뉴스 타입(네이버 or 다음)

    @Column(name="subject")
    private String subject; // 뉴스 제목

    @Column(name="newsUrl")
    private String newsUrl; // 뉴스 url

    public News() {

    }

    public News(Long id, String type, String subject, String newsUrl) {
        this.id = id;
        this.type = type;
        this.subject = subject;
        this.newsUrl = newsUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "subject='" + subject + '\'' +
                ", newsUrl='" + newsUrl + '\'' +
                '}';
    }
    
}