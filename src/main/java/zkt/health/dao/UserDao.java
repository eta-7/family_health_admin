package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.User;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);
    List<User> findByUsernameAndPassword(String username,String password);
}

