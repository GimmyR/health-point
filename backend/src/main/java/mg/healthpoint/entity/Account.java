package mg.healthpoint.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
	@SequenceGenerator(name = "account_sequence_generator", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	private Integer id;
	
	private String username;
	
	private String password;
	
	private String firstname;
	
	private String lastname;
	
	private String gender;
	
	private LocalDate dateOfBirth;
	
	private String address;
	
	private String contact;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable
	private List<Role> roles = new ArrayList<Role>();

}
