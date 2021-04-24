package springframework.model;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "owners")
@NoArgsConstructor
public class Owner extends Person {
    @Column(name = "address")
    private String address ;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(Long id,String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id,firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = pets;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Owner;
    }

    public Pet getPet(String name){
        Set<Pet> petSet = pets.stream().filter(pet -> !pet.isNew()&& pet.getName().equalsIgnoreCase(name)).collect(Collectors.toSet());
       if(!CollectionUtils.isEmpty(petSet)){
           return petSet.iterator().next();
       }else {
           return null;
       }

    }

}
