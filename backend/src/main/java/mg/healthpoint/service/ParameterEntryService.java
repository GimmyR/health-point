package mg.healthpoint.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.dto.SaveEntryRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.ParameterEntry;
import mg.healthpoint.repository.ParameterEntryRepository;
import mg.healthpoint.repository.ParameterRepository;


@Service
@AllArgsConstructor
@Transactional
public class ParameterEntryService {
	
	private ParameterEntryRepository parameterEntryRepository;
	private ParameterRepository parameterRepository;
	
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
	
	public ParameterEntry save(SaveEntryRequest form) throws NotFoundException, BadRequestException {
		
		Optional<Parameter> opt = this.parameterRepository.findById(form.parameterId());
		
		if(opt.isEmpty())
			throw new NotFoundException("Parameter not found");
		
		Parameter param = opt.get();
		ParameterEntry entry = null;
		
		if(form.value() < param.getMin() || form.value() > param.getMax())
			throw new BadRequestException(String.format("Entry value should be between %f and %f", param.getMin(), param.getMax()));
		
		if(form.id() != null)
			entry = this.findUniqueById(form.id());
		
		else entry = new ParameterEntry();
		
		entry.editParameter(param);
		entry.editEntryDate(form.entryDate());
		entry.editValue(form.value());
		
		return parameterEntryRepository.save(entry);
		
	}
	
	public void delete(ParameterEntry parameterEntry) {
		
		parameterEntryRepository.delete(parameterEntry);
		
	}

}
