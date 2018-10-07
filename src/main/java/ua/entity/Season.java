package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "season", indexes = @Index(columnList = "name", unique = true))
public class Season extends AbstractEntityName {

    @OneToMany(mappedBy = "season")
    private List<ClothingModel> clothingModels = new ArrayList<>();

    public Season() {
    }

    public Season(String name) {
        super(name);
    }

}