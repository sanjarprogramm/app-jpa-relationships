package uz.pdp.appjparelationships.controller;

import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.UniversityDto;
import uz.pdp.appjparelationships.repository.AddressRepository;
import uz.pdp.appjparelationships.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "university")

public class ControllerUniversity {

    @Autowired  //BINDAN OBEKT OLADI
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;
    //-----------------------------------------------------------------------------------
    // UNIVERSITETLAR RO'YXATINI OLISH

    @GetMapping
    public List<University> getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }
    //-------------------------------------------------------------------
    //UNIVERSITET QO'SHISH

    @PostMapping(value = "/create")
    public String addUniversity(@RequestBody UniversityDto universityDto) {



        boolean exists = addressRepository.existsByCityAndDistrictAndStreet(
                universityDto.getCity(),
                universityDto.getDistrict(),
                universityDto.getStreet());
        if (exists){
            return "bu adres bant";
        }
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        addressRepository.save(address);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(address);
        universityRepository.save(university);

        return "Universitet qo'shildi";


    }
    //---------------------------------------------------------------
    //UNIVERSITETNI TAXRIRLASH

    @PutMapping(value = "/update/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {


        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            Address address = Optional.ofNullable(university.getAddress()).orElse(new Address());
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            Address newAddress = addressRepository.save(address);
            university.setName(universityDto.getName());
            university.setAddress(newAddress);
            universityRepository.save(university);
            return "Universitet yangilandi";
        }

        return "Universitet topilmadi";
    }
    // UNIVERSITET O'CHIRISH

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public String deleteUniversity(@PathVariable Integer id) {

        universityRepository.deleteById(id);
        return "Universitet o'chirildi";
    }

}
