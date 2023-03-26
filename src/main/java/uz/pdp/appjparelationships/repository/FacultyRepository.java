package uz.pdp.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationships.entity.Faculty;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    boolean existsByName(String name);


}
