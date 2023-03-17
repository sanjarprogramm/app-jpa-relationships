package uz.pdp.appjparelationships.controller;

import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ControllerUniversity {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @RequestMapping( value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities (){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }
    @RequestMapping(value = "/university",method = RequestMethod.POST )
    public String addUniversity (@RequestBody UniversityDto universityDto){
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);


        return "Universitet qo'shildi";

    }
    @RequestMapping(value = "/university/{id}" , method = RequestMethod.PUT)
    public String editUniversity (@PathVariable Integer id ,@RequestBody UniversityDto universityDto ){


        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            University editinguniversity = optionalUniversity.get();
            Address address = Optional.ofNullable(editinguniversity.getAddress()).orElse(new Address());
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            Address savedAddress = addressRepository.save(address);
            editinguniversity.setName(universityDto.getName());
            editinguniversity.setAddress(savedAddress);
             universityRepository.save(editinguniversity);
             return "Universitet yangilandi";
        }

      return "Universitet topilmadi";
    }

    @RequestMapping (value = "/university/{id}" , method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUniversity (@PathVariable Integer id){

        universityRepository.deleteById(id);
        return "Universitet o'chirildi";
    }

}
