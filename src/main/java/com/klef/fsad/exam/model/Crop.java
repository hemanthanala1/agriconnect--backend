package com.klef.fsad.exam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "crops")
public class Crop {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    private String name;
    private String variety;
    private Float area;
    private String soilType;
    private String irrigationType;
    private String harvestDate;
    
    @Column(nullable = true)  // 🔥 MAKE PRICE NULLABLE
    private Float price;
    
    // Constructors
    public Crop() {}
    
    public Crop(Long userId, String name, String variety, Float area, String soilType, String irrigationType, String harvestDate) {
        this.userId = userId;
        this.name = name;
        this.variety = variety;
        this.area = area;
        this.soilType = soilType;
        this.irrigationType = irrigationType;
        this.harvestDate = harvestDate;
        this.price = 0f;  // Default price
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getVariety() { return variety; }
    public void setVariety(String variety) { this.variety = variety; }

    public Float getArea() { return area; }
    public void setArea(Float area) { this.area = area; }

    public String getSoilType() { return soilType; }
    public void setSoilType(String soilType) { this.soilType = soilType; }

    public String getIrrigationType() { return irrigationType; }
    public void setIrrigationType(String irrigationType) { this.irrigationType = irrigationType; }

    public String getHarvestDate() { return harvestDate; }
    public void setHarvestDate(String harvestDate) { this.harvestDate = harvestDate; }

    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }
}