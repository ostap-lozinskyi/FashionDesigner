package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.TypeOfClothes;

public interface TypeOfClothesRepository extends JpaNameRepository<TypeOfClothes>,
        JpaSpecificationExecutor<TypeOfClothes> {

    @Query("SELECT t.name FROM TypeOfClothes t")
    List<String> findAllTypeOfClothesNames();

}