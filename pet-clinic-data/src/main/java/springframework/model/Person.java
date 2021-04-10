package springframework.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class Person extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Person;
    }

}
