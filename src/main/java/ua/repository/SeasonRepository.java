package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Season;

public interface SeasonRepository extends JpaNameRepository<Season>, JpaSpecificationExecutor<Season> {

    @Query("SELECT s.name FROM Season s")
    List<String> findAllSeasonsNames();

}