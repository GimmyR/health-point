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
public class EntryDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entry_detail_sequence_generator")
	@SequenceGenerator(name = "entry_detail_sequence_generator", sequenceName = "ENTRY_DETAIL_SEQ", allocationSize = 1)
	private Integer id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private ParameterEntry entry;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Parameter parameter;
	
	private Double value;

}