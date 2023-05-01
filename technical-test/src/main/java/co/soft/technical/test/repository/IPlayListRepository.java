package co.soft.technical.test.repository;
import co.soft.technical.test.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPlayListRepository extends JpaRepository<PlayList, Long> {
    Optional<PlayList> findByName(String name);
    void deleteByName(String name);
}
