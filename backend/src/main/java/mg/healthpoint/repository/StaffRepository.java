package mg.healthpoint.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.healthpoint.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
	
	@Query("select s from Staff s join fetch s.account where s.admin = false")
	List<Staff> findAllWithAccountWithoutAdmin();
	
	@Query("select s from Staff s join fetch s.account where s.id = :id")
	Optional<Staff> findByIdWithAccount(@Param("id") Integer id);
	
	Optional<Staff> findByAccountId(Integer id);
	
	@Query("select s from Staff s where s.account.username = :username")
	Optional<Staff> findByAccountUsername(@Param("username") String username);
	
	Optional<Staff> findByAdmin(Boolean admin);

}
