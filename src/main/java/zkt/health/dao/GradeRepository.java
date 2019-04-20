package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zkt.health.domain.Grade;

import java.util.List;

@Repository
public interface GradeRepository  extends JpaRepository<Grade, Long> {
    List<Grade> findByUserId(long id);
}
