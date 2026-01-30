package mg.healthpoint.entity;

import java.time.LocalDateTime;
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
public class ParameterEntry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_entry_sequence_generator")
	@SequenceGenerator(name = "parameter_entry_sequence_generator", sequenceName = "PARAMETER_ENTRY_SEQ", allocationSize = 1)
	private Integer id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Parameter parameter;
	
	private LocalDateTime entryDate;
	
	private Double value;

}