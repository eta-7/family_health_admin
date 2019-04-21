package zkt.health.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zkt.health.domain.Archive;

@Repository
public interface ArchiveDao extends JpaRepository<Archive, Integer> {
    Archive findByUserId(Integer userId);
}
