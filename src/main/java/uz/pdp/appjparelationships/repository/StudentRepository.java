package uz.pdp.appjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationships.entity.Student;

public interface StudentRepository extends JpaRepository<Student , Integer> {
}
