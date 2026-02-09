package mg.healthpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	Optional<Patient> findByAccountId(Integer id);
	
	@Query("select p from Patient p where p.account.username = :username")
	Optional<Patient> findByAccountUsername(@Param("username") String username);
	
	@Query("""
	select p from Patient p
	left join fetch p.parameters
	where p.account.username = :username		
	""")
	Optional<Patient> findWithParametersByUsername(@Param("username") String username);

}
