package com.klef.fsad.exam.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ EXISTING FIELDS (UNCHANGED)
    private String name;
    private String type;
    private String season;
    private double price;
    private String description;

    // 🔥 NEW FIELDS (ADDED SAFELY)

    private String variety;

    private Double areaPlanted;

    private String soilType;

    private String irrigationType;

    private LocalDate plantingDate;

    private LocalDate expectedHarvestDate;

    private String currentStatus = "Active";

    // ✅ GETTERS & SETTERS (UPDATED)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVariety() { return variety; }
    public void setVariety(String variety) { this.variety = variety; }

    public Double getAreaPlanted() { return areaPlanted; }
    public void setAreaPlanted(Double areaPlanted) { this.areaPlanted = areaPlanted; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public String getIrrigationType() { return irrigationType; }
    public void setIrrigationType(String irrigationType) { this.irrigationType = irrigationType; }

    public LocalDate getPlantingDate() { return plantingDate; }
    public void setPlantingDate(LocalDate plantingDate) { this.plantingDate = plantingDate; }

    public LocalDate getExpectedHarvestDate() { return expectedHarvestDate; }
    public void setExpectedHarvestDate(LocalDate expectedHarvestDate) { this.expectedHarvestDate = expectedHarvestDate; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }
}