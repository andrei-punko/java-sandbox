package by.andd3dfx.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}
