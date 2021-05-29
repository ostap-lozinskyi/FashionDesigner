package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.SectionOfClothes;
import ua.entity.TypeOfClothes;

public interface SectionOfClothesRepository extends JpaNameRepository<SectionOfClothes>, JpaSpecificationExecutor<SectionOfClothes> {

    @Query("SELECT s.name FROM SectionOfClothes s")
    List<String> findAllSectionOfClothesNames();

}