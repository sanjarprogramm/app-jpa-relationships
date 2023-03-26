package uz.pdp.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationships.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Integer> {
    boolean existsByName(String name);

}
