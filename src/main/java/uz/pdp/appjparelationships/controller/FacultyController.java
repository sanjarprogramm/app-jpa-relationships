package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.FacultyDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    // Facultet qo'shish -----> jadvalga yangi fakultet qo'shish

    @PostMapping(value = "/add")
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        try {
            Faculty faculty = new Faculty();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()) {
                return "Bunday Universitet jadvalda yo'q";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
        } catch (Exception e) {
            return "Siz tanlagan univerda bu fakultet avvaldan bor";
        }
            return "Facultet qo'shildi";
    }

    @GetMapping
    public List<Faculty> register() {
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList;

    }
    // taxrirlash

    @PutMapping(value = "/update/{id}")
    public String edFacultyById(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            boolean existsByName = facultyRepository.existsByName(facultyDto.getName());
            if (!existsByName) {
                Faculty faculty = optionalFaculty.get();
                faculty.setName(facultyDto.getName());
                facultyRepository.save(faculty);
                return "Fakultet nomi o'zgartirildi";
            }
        }
        return "bunday fakultet avvaldan bor";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            facultyRepository.deleteById(id);
            return "faculty deleted";
        }

        return "Bunday fakultet yo'q";
    }

}
