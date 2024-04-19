package world.gta.saaa.aircraft.domain.relation;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import world.gta.saaa.aircraft.domain.person.Person;
import world.gta.saaa.aircraft.domain.user.User;

@Entity
@Table(name = "relations")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Relation {

    public Relation(User user, Person person) {
        this.user = user;
        this.person = person;
        this.id = new RelationKey(user.getId(), person.getId());
    }

    @EmbeddedId
    RelationKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;
    
    @ManyToOne
    @MapsId("personId")
    @JoinColumn(name = "person_id")
    Person person;


}
