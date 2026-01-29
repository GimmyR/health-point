package mg.healthpoint.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor @AllArgsConstructor
public class Parameter {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_sequence_generator")
	@SequenceGenerator(name = "parameter_sequence_generator", sequenceName = "PARAMETER_SEQ", allocationSize = 1)
	private Integer id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Patient patient;
	
	@Column(nullable = false)
	private String name;
	
	private String unit;
	
	@OneToMany(mappedBy = "parameter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EntryDetail> details = new ArrayList<EntryDetail>();

}