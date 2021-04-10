package springframework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.model.*;
import springframework.services.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService , PetTypeService petTypeService , SpecialtyService specialtyService,VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count  = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
     }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Suregery");
        Speciality savedSurgery = specialtyService.save(surgery);


        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry =specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Hicham ");
        owner1.setLastName("Cheikhaoui");
        owner1.setAddress("68 azbezt db el cadi");
        owner1.setCity("Marrakech");
        owner1.setTelephone("0618073196");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthday(LocalDate.now());
        mikesPet.setName("rosco");
        owner1.getPets().add(mikesPet);


        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Nizar");
        owner2.setLastName("Benchouchou");
        owner1.setAddress("acharaf 09");
        owner1.setCity("Marrakech");
        owner1.setTelephone("0710161016 ");

        Pet nizarCat = new Pet();
        mikesPet.setPetType(savedCatPetType);
        mikesPet.setOwner(owner2);
        mikesPet.setBirthday(LocalDate.now());
        mikesPet.setName("cato");
        owner2.getPets().add(nizarCat);

        System.out.println("Loaded Owners....");

        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("smeally cat");
        catVisit.setPet(nizarCat);
        Visit visit = visitService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().addAll(Arrays.asList(dentistry,surgery));
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
