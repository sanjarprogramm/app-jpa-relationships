package uz.pdp.appjparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private String street;

}
