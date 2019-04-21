package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Pressure;
import zkt.health.domain.Pressure;

import java.util.List;

public interface PressureDao extends JpaRepository<Pressure, Integer> {
    List<Pressure> findByPressureDateAndUserId(String fatDate, Integer userId);
    void deleteByPressureDateAndUserId(String fatDate, Integer userId);
    List<Pressure> findByUserId(Integer useId);
}

