package org.example.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    public int ID;

    @Column(name= "Name")
    public String Name;
    @Column(name = "Location")
    public String Location;
    @Column(name="Employee")
    public int Employee;

    @OneToMany(mappedBy = "manufacture", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    public List<Phone> lstPhone;
    public Manufacture(int ID, String name, String location, int employee) {
        this.ID = ID;
        Name = name;
        Location = location;
        Employee = employee;
    }

    public Manufacture() {
    }

    public Manufacture(String name, String location, int employee) {
        Name = name;
        Location = location;
        Employee = employee;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getEmployee() {
        return Employee;
    }

    public void setEmployee(int employee) {
        Employee = employee;
    }

    public List<Phone> getLstPhone() {
        return lstPhone;
    }

    public void addPhone(Phone phone) {
        if(lstPhone == null) {
            lstPhone = new ArrayList<>();
        }
        lstPhone.add(phone);
    }
    @Override
    public String toString() {
        return "Manufacture{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Location='" + Location + '\'' +
                ", Employee=" + Employee +
                ", lstPhone=" + lstPhone +
                '}';
    }
}
