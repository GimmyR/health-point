package mg.healthpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	Optional<Patient> findByAccountId(Integer id);

}
