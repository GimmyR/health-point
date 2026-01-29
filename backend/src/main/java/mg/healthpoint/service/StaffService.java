package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.Staff;
import mg.healthpoint.repository.StaffRepository;


@Service
@AllArgsConstructor
@Transactional
public class StaffService {
	
	private StaffRepository staffRepository;
	
	public List<Staff> findAll() {
		
		return staffRepository.findAll();
		
	}
	
	public Staff findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Staff> opt = staffRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Staff not found");
		
	}
	
	public Staff save(Staff staff) {
		
		return staffRepository.save(staff);
		
	}
	
	public void delete(Staff staff) {
		
		staffRepository.delete(staff);
		
	}

}
