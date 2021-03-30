package springframework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.model.Owner;
import springframework.model.Pet;
import springframework.model.PetType;
import springframework.model.Vet;
import springframework.services.OwnerService;
import springframework.services.PetTypeService;
import springframework.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService , PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatPetType = petTypeService.save(dog);


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

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
