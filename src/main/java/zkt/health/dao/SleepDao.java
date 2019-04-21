package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Sleep;

import javax.transaction.Transactional;
import java.util.List;

public interface SleepDao extends JpaRepository<Sleep, Integer> {
    @Transactional
    void deleteAllByStartTime(String startTime);
    List<Sleep> findByStartTime(String startTime);
    List<Sleep> findByStartTimeAndUserId(String startTime,Integer userId);
}
