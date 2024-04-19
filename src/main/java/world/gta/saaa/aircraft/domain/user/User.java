package world.gta.saaa.aircraft.domain.user;

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
import world.gta.saaa.aircraft.domain.relation.Relation;

@Entity(name = "user")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class User {

    public User(UserDTO data) {
        this.login = data.login();
        this.password = data.password();
        this.saaa = false;
        this.admin = false;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private boolean saaa;
    private boolean admin;

    @OneToMany(mappedBy = "user")
    public Set<Relation> userRelations;

    // GetAuthority will rely on saaa/admin booleans from role_user to role_staff to role_admin
    
}
