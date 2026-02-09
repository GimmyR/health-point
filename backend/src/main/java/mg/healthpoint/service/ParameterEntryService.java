package mg.healthpoint.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.ParameterEntry;
import mg.healthpoint.repository.ParameterEntryRepository;


@Service
@AllArgsConstructor
@Transactional
public class ParameterEntryService {
	
	private ParameterEntryRepository parameterEntryRepository;
	
	public List<ParameterEntry> findAll() {
		
		return parameterEntryRepository.findAll();
		
	}
	
	public ParameterEntry findUniqueById(Integer id) throws NotFoundException {
		
		Optional<ParameterEntry> opt = parameterEntryRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Parameter Entry not found");
		
	}
	
	public List<LocalDateTime> findDistinctEntryDatesByPatientId(Integer id) {
		
		return parameterEntryRepository.findDistinctEntryDatesByPatientId(id);
		
	}
	
	public ParameterEntry save(ParameterEntry parameterEntry) {
		
		return parameterEntryRepository.save(parameterEntry);
		
	}
	
	public void delete(ParameterEntry parameterEntry) {
		
		parameterEntryRepository.delete(parameterEntry);
		
	}

}
