package springframework.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetType extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
