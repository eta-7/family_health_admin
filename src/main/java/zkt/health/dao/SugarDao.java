package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Sugar;

import java.util.List;

public interface SugarDao extends JpaRepository<Sugar, Integer> {
    List<Sugar> findBySugarDateAndUserId(String fatDate, Integer userId);
    void deleteBySugarDateAndUserId(String fatDate, Integer userId);
    List<Sugar> findByUserId(Integer useId);
}
