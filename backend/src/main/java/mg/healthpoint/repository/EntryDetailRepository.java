package mg.healthpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.EntryDetail;

@Repository
public interface EntryDetailRepository extends JpaRepository<EntryDetail, Integer> {

}
