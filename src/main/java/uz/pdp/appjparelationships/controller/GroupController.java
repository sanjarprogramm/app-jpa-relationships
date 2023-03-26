package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjparelationships.entity.Faculty;
import uz.pdp.appjparelationships.entity.Group;
import uz.pdp.appjparelationships.payload.GroupDto;
import uz.pdp.appjparelationships.repository.FacultyRepository;
import uz.pdp.appjparelationships.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/group")

public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @PostMapping(value = "/creat")
    public String createGroup(@RequestBody GroupDto groupDto) {
        try {
            Group group = new Group();
            group.setName(groupDto.getName());
            Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
            if (!optionalFaculty.isPresent()) {
                return "tanlangan fakultet bazada yoq";
            }
            Faculty faculty = optionalFaculty.get();
            group.setFaculty(faculty);
            groupRepository.save(group);
        } catch (Exception e) {
            return "Tanlangan fakultetda bu guruh mavjud";
        }
        return "grux qushildi";

    }


    @GetMapping
    public List<Group> registerGroup() {
        List<Group> groupList = groupRepository.findAll();
        return groupList;

    }

    @PutMapping(value ="/editing/{id}")
    public String editingGroup(@PathVariable Integer id,@RequestBody GroupDto groupDto){

        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (!optionalGroup.isPresent()){
            return "Bu id ga ega gurux ro'xatda yo'q";
        }
        Group group = optionalGroup.get();
        group.setName(groupDto.getName());
        groupRepository.save(group);
        return "Guruh nomi o'zgartirildi";


    }
    @DeleteMapping(value = "/deleting/{id}")
    public String deletingGroup(@PathVariable Integer id){
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()){
            groupRepository.deleteById(id);
            return "guruh o'chirildi";
        }
        return "guruh topilmadi";

    }

}
