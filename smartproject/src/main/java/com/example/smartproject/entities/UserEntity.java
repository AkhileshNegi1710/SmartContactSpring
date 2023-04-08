package com.example.smartproject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserTable")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
//    email will become unique key
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private boolean status;
    private String imageUrl;
    @Column(length = 500)
    private String Userdescription;

//    One (user) to Many(Contact)
//    When user's data is saved, all user's contact will be saved
//    When user's data is removed, all contact will be removed of the same user.
//    Everytime data is available so use fetch = FetchType.EAGER else whenever data is needed then use fetch = FetchType.LAZY
//    LAZY = fetch when needed
//    EAGER = fetch immediately


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<ContactEntity> contactEntities=new ArrayList<>();



    public List<ContactEntity> getContactEntities() {
        return contactEntities;
    }

    public void setContactEntities(List<ContactEntity> contactEntities) {
        this.contactEntities = contactEntities;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return Userdescription;
    }

    public void setDescription(String Userdescription) {
        this.Userdescription = Userdescription;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status=" + status +
                ", imageUrl='" + imageUrl + '\'' +
                ", Userdescription='" + Userdescription + '\'' +
                ", contactEntities=" + contactEntities +
                '}';
    }
}
