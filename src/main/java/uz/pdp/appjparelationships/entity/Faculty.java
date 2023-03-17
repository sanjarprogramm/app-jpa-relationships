package uz.pdp.appjparelationships.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    private String name ;


    @ManyToOne
    private University university ;

}
