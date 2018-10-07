package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.SectionOfClothes;
import ua.entity.TypeOfClothes;

public interface SectionOfClothesRepository extends JpaNameRepository<SectionOfClothes>, JpaSpecificationExecutor<SectionOfClothes> {

    @Query("SELECT s FROM SectionOfClothes s WHERE s.id=?1")
    SectionOfClothes findSectionOfClothesById(Integer id);

    @Query("SELECT s.name FROM SectionOfClothes s")
    List<String> findAllSectionOfClothesNames();

    @Query("SELECT new ua.model.view.SectionOfClothesView(s.id, s.name) FROM SectionOfClothes s WHERE s.id=?1")
    TypeOfClothes findSectionOfClothesViewById(Integer id);

}