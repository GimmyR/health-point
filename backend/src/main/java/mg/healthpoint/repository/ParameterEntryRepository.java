package mg.healthpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.ParameterEntry;

@Repository
public interface ParameterEntryRepository extends JpaRepository<ParameterEntry, Integer> {

}
