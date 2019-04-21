package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Weight;

import java.util.List;

public interface WeightDao extends JpaRepository<Weight, Integer> {
    List<Weight> findByWeightDateAndUserId(String fatDate, Integer userId);
    void deleteByWeightDateAndUserId(String fatDate, Integer userId);
    List<Weight> findByUserId(Integer useId);
}
