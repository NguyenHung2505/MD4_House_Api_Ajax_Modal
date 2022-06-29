package com.example.md4minitesttnha.model;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "khong bo trong")
    private String name;
    @NotBlank(message = "khong bo trong")
    private String address;
    @Min(value = 1 ,message = "nho nhat la 1")
    @Max(value = 10, message = "lon nhat la 10")
    private int bedrom;
    @Min(value = 1 ,message = "nho nhat la 1")
    @Max(value = 3, message = "lon nhat la 10")
    private int bathrom;
    private int price;
    private String image;

    public House() {
    }

    public House(Long id, String name, String address, int bedrom, int bathrom, int price, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.bedrom = bedrom;
        this.bathrom = bathrom;
        this.price = price;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBedrom() {
        return bedrom;
    }

    public void setBedrom(int bedrom) {
        this.bedrom = bedrom;
    }

    public int getBathrom() {
        return bathrom;
    }

    public void setBathrom(int bathrom) {
        this.bathrom = bathrom;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
