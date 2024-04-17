package world.gta.saaa.aircraft.domain.aircraft;

import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import world.gta.saaa.aircraft.domain.classification.Classification;
import world.gta.saaa.aircraft.domain.person.Person;

@Entity(name = "aircraft")
@Table(name = "aircrafts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Aircraft {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;

    @Column(name = "tail_number")
    private String tailNumber;

    @Enumerated(EnumType.STRING)
    private Classification type;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person owner;

    public String getEverything() {
        return this.brand + " " 
        + this.model + " "
        + this.tailNumber + " " 
        + this.getOwner().getName();
    }

    public boolean validTailNumber() {
        return Pattern.matches(
            "^N[1-9]((\\d{0,4})|(\\d{0,3}[A-HJ-NP-Z])|(\\d{0,2}[A-HJ-NP-Z]{2}))$", 
            this.tailNumber
        );
    }
    
}

