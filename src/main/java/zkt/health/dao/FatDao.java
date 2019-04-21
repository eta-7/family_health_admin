package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zkt.health.domain.Fat;

import java.util.List;

public interface FatDao extends JpaRepository<Fat, Integer> {
    List<Fat> findByFatDateAndUseId(String fatDate,Integer userId);
    void deleteByFatDateAndUseId(String fatDate,Integer userId);
    List<Fat> findByUseId(Integer useId);
}
