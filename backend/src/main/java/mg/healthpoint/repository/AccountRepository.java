package mg.healthpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mg.healthpoint.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	Account findByUsername(String username);

}
