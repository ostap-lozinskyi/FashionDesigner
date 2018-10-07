package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Color;

public interface ColorRepository extends JpaNameRepository<Color>, JpaSpecificationExecutor<Color> {

    @Query("SELECT c.name FROM Color c")
    List<String> findAllColorsNames();

}