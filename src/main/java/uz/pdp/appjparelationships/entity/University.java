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
public class University {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;



    private String name ;



    @OneToOne(optional = false ,cascade = CascadeType.ALL)
    private Address address ;
}
