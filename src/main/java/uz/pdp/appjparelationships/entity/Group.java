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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groups")
public class Group {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;



    private String name ;

    @ManyToOne
    private Faculty faculty;


//    @OneToMany
//    private List<Student>students;

}
