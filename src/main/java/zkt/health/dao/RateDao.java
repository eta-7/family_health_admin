package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Rate;
import zkt.health.domain.Rate;

import java.util.List;

public interface RateDao extends JpaRepository<Rate, Integer> {
    List<Rate> findByRateDateAndUserId(String fatDate, Integer userId);
    void deleteByRateDateAndUserId(String fatDate, Integer userId);
    List<Rate> findByUserId(Integer useId);
}
