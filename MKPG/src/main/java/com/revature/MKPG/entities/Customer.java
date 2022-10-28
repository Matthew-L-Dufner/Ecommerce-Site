package com.revature.MKPG.entities;

//import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    @NotNull(message = "First name is required")
    @Size(min = 1)
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @NotNull(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9\\.\\-_]+@[A-Za-z0-9\\-]+\\.[A-Za-z]{2,5}$",message = "Email not valid")
    @Column
    private String email;
    @NotNull(message = "Password is required")
    @Column
    private String password;
    @Column
    private String phone;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column
    @Temporal(TemporalType.DATE)
    private Date created;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToOne(mappedBy = "customer")
    @JsonBackReference
    private Cart cart;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String password, String phone, Date birthDate, Date created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.created = created;
    }

    public Customer(Integer customerId, String firstName, String lastName, String email, String password, String phone, Date birthDate, Date created, List<Address> address, Cart cart) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.created = created;
        this.address = address;
        this.cart = cart;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public void addAddress(Address address) {
        if (this.address == null) {
            this.address = new ArrayList<>();
        }
        getAddress().add(address);
        address.setCustomer(this);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", created=" + created +
                ", address=" + address +
                '}';
    }
}
