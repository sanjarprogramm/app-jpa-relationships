package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //Create ---> jadvalga yangi fan qo'shish

    @PostMapping
    public String add(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "bunday fan jadvalda bor";
        subjectRepository.save(subject);


        return "Fan qo'shildi";
    }
          // Read ----> Jadvaldan fanlar ro'yhatini olish
    @GetMapping
    public List<Subject> subjectList() {
        List<Subject> listSubject = subjectRepository.findAll();

        return listSubject;
    }

    //Update  -----> jadvaldagi fanlarga o'zgartirish kiritish
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public String updateSubject (@PathVariable Integer id , @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject newSubject = optionalSubject.get();
            newSubject.setName(subject.getName());
            subjectRepository.save(newSubject);
            return "Jadvaldagi fan taxrirlandi";
        }


        return "bunday fan jadvalda yo'q";
    }
    //DELETE ---------> JADVALDAGI FANLAR ORASIDAN ID BERILGAN FANNI O'CHIRISH

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteSub (@PathVariable Integer id){
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()){
            subjectRepository.deleteById(id);
            return "Bu fan Jadvaldan o'chirildi";
        }
        return "Bunday id Ga ega fan jadvalda yo'q";
    }

}
