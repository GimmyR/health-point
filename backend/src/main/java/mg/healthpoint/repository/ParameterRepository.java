package mg.healthpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
	
	@Query("select p from Parameter p left join fetch p.details details where p.id = :id order by details.entryDate ASC")
	Optional<Parameter> findWithEntriesById(@Param("id") Integer id);

}
