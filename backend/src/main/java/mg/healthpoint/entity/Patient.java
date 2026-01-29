package mg.healthpoint.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence_generator")
	@SequenceGenerator(name = "patient_sequence_generator", sequenceName = "PATIENT_SEQ", allocationSize = 1)
	private Integer id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;
	
	private String room;
	
	private String diagnosis;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Parameter> parameters = new ArrayList<Parameter>();
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<ParameterEntry> entries = new ArrayList<ParameterEntry>();

}