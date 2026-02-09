package mg.healthpoint.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.ParameterEntry;

@Repository
public interface ParameterEntryRepository extends JpaRepository<ParameterEntry, Integer> {
	
	@Query("select distinct e.entryDate from ParameterEntry e where e.parameter.patient.id = :id order by e.entryDate asc")
	List<LocalDateTime> findDistinctEntryDatesByPatientId(@Param("id") Integer id);

}
