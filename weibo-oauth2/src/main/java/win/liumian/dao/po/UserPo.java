package win.liumian.dao.po;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by liumian on 2017/4/4.
 */
@Entity
public class UserPo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userName;

    private String pwd;

    private String uid;


}
