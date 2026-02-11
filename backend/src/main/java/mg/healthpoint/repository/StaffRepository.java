package mg.healthpoint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.healthpoint.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	Optional<Staff> findByAccountId(Integer id);
	
	@Query("select s from Staff s where s.account.username = :username")
	Optional<Staff> findByAccountUsername(@Param("username") String username);

}
