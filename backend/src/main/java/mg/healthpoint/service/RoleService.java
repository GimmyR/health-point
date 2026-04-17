package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.Role;
import mg.healthpoint.repository.RoleRepository;


@Service
@AllArgsConstructor
@Transactional
public class RoleService {
	
	private RoleRepository roleRepository;
	
	public List<Role> findAll() {
		
		return roleRepository.findAll();
		
	}
	
	public Role findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Role> opt = roleRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Role not found");
		
	}
	
	public Role findUniqueByName(String name) throws NotFoundException {
		
		Optional<Role> opt = roleRepository.findByName(name);
		
		if(opt.isEmpty())
			throw new NotFoundException("Role not found");
		
		return opt.get();
		
	}
	
	public Role save(Role role) {
		
		return roleRepository.save(role);
		
	}
	
	public void delete(Role role) {
		
		roleRepository.delete(role);
		
	}

}
