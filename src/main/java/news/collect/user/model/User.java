package news.collect.user.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(name="userId")
    private String userId; // 아이디

    @Column(name="userName")
    private String userName; // 이름

    @Column(name="password")
    private String password; // 비밀번호

    @Column(name="userRoles")
    private String userRoles; // 권한

}