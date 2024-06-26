package world.gta.saaa.aircraft.domain.person;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import world.gta.saaa.aircraft.domain.relation.Relation;

@Entity(name = "person")
@Table(name = "persons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Person {

    public Person(PersonDTO data) {
        this.name = data.name();
        this.representative = data.representative();
        this.phone = data.phone();
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String representative;
    private Long phone;

    @OneToMany(mappedBy = "person")
    public Set<Relation> personRelations;

}
