package mg.healthpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	Optional<Staff> findByAccountId(Integer id);

}
