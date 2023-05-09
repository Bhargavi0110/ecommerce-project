package com.example.ecommerce.entities;



import javax.persistence.*;

@Entity
@Table(name="images")
public class ProductImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public ProductImages() {
    }

    public ProductImages(Integer id, String name, byte[] imageData) {
        this.id = id;
        this.name = name;
        this.imageData = imageData;
    }
}
