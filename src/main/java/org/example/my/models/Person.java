package org.example.my.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters!")
    @Column(name = "name")
    private String name;

    @Min(value = 0, message = "Age should be greater than 0!")
    @Column(name = "age")
    private int age;

    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "email should be valid")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}",
            message = "Your address should be in that form: Country, City, Code(6 digits)")
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "person")
    private List<Item> items;

    public Person(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }
}
