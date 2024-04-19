package world.gta.saaa.aircraft.domain.relation;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RelationKey {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "person_id")
    Long personId;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RelationKey)) return false;

        RelationKey other = (RelationKey) o;
        String thisCode = this.userId.toString() + this.personId.toString();
        String otherCode = other.userId.toString() + other.personId.toString();
        return thisCode == otherCode;
    }

    @Override
    public final int hashCode() {
        String thisCode = this.userId.toString() + this.personId.toString();
        return thisCode.hashCode();
    }
    
}
