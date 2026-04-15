package mg.healthpoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select a from Account a left join fetch a.roles where a.username = :username")
	Account findWithRolesByUsername(@Param("username") String username);
	
	@Query("select a from Account a left join fetch a.roles where a.id not in (select s.account.id from Staff s where s.admin = true)")
	List<Account> findAllWithRolesWithoutAdmin();

}
