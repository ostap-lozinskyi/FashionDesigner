package ua.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "sectionOfClothes", indexes = @Index(columnList = "name", unique = true))
public class SectionOfClothes extends AbstractEntityName {

    public SectionOfClothes() {
    }

    public SectionOfClothes(String name) {
        super(name);
    }

}