package org.example.Domain;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public String ID;

    @Column(name = "Name",nullable = false)
    public String Name;

    @Column(name = "Price", nullable = false)
    public int Price;

    @Column(name = "Color", nullable = false)
    public String Color;

    @Column(name = "Country")
    public String Country;
    @Column(name = "Quantity")
    public int Quantity;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "manufactureID")
    public Manufacture manufacture;

    public Phone(String ID, String name, int price, String color, String country, int quantity) {
        this.ID = ID;
        Name = name;
        Price = price;
        Color = color;
        Country = country;
        Quantity = quantity;
    }

    public Phone(String name, int price, String color, String country, int quantity) {
        Name = name;
        Price = price;
        Color = color;
        Country = country;
        Quantity = quantity;
    }

    public Phone() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                ", Color='" + Color + '\'' +
                ", Country='" + Country + '\'' +
                ", Quantity=" + Quantity +
                '}';
    }


}
