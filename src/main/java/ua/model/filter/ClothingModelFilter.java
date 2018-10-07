package ua.model.filter;

import java.util.ArrayList;
import java.util.List;

public class ClothingModelFilter {

    private String search = "";

    private List<String> sectionOfClothesName = new ArrayList<>();

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<String> getSectionOfClothesName() {
        return sectionOfClothesName;
    }

    public void setSectionOfClothesName(List<String> sectionOfClothesName) {
        this.sectionOfClothesName = sectionOfClothesName;
    }
}