package springframework.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.model.Owner;
import springframework.model.Vet;
import springframework.services.OwnerService;
import springframework.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1l);
        owner1.setFirstName("Hicham ");
        owner1.setLastName("Cheikhaoui");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner1.setId(2l);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        System.out.println("Loaded Owners....");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1l);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2l);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
