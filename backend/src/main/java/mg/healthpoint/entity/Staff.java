package mg.healthpoint.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class Staff {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_sequence_generator")
	@SequenceGenerator(name = "staff_sequence_generator", sequenceName = "STAFF_SEQ", allocationSize = 1)
	private Integer id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;
	
	private String profession;

}