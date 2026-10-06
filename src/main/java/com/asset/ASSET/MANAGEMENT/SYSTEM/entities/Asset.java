package com.asset.ASSET.MANAGEMENT.SYSTEM.entities;
import jakarta.persistence.*;
@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false, unique = true)
    private String serialMac;

    @Column(nullable = false)
    private String assetType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssetLocation popLocation;

    @Column(nullable = false)
    private String popHead;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, unique = true)
    private String number;


    public Asset() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getSerialMac() {
        return serialMac;
    }

    public String getAssetType() {
        return assetType;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public AssetLocation getPopLocation() {
        return popLocation;
    }

    public String getPopHead() {
        return popHead;
    }

    public Integer getYear() {
        return year;
    }

    public String getNumber() {
        return number;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialMac(String serialMac) {
        this.serialMac = serialMac;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public void setStatus(AssetStatus status) {
        this.status = status;
    }

    public void setPopLocation(AssetLocation popLocation) {
        this.popLocation = popLocation;
    }

    public void setPopHead(String popHead) {
        this.popHead = popHead;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}