package win.liumian.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import win.liumian.dao.po.UserPo;

/**
 * Created by liumian on 2017/4/4.
 */
public interface UserDao extends JpaRepository<UserPo, Long> {



}
