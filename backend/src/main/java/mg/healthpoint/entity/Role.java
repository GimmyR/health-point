package mg.healthpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence_generator")
	@SequenceGenerator(name = "role_sequence_generator", sequenceName = "ROLE_SEQ", allocationSize = 1)
	private Integer id;
	
	private String name;

}