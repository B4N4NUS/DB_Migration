package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries", schema = "olympics")
public class Country {
    @Id
    @Column(name = "country_id", nullable = false, length = 3)
    private String id;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "area_sqkm")
    private Integer areaSqkm;

    @Column(name = "population")
    private Integer population;

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getAreaSqkm() {
        return areaSqkm;
    }

    public void setAreaSqkm(Integer areaSqkm) {
        this.areaSqkm = areaSqkm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + areaSqkm + " " +population;
    }
}