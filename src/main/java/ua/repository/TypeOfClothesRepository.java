package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.TypeOfClothes;

public interface TypeOfClothesRepository extends JpaNameRepository<TypeOfClothes>,
        JpaSpecificationExecutor<TypeOfClothes> {

    @Query("SELECT t FROM TypeOfClothes t WHERE t.id=?1")
    TypeOfClothes findTypeOfClothesById(Integer id);

    @Query("SELECT t.name FROM TypeOfClothes t")
    List<String> findAllTypeOfClothesNames();

    @Query("SELECT new ua.model.view.TypeOfClothesView(t.id, t.name) FROM TypeOfClothes t WHERE t.id=?1")
    TypeOfClothes findTypeOfClothesViewById(Integer id);

}