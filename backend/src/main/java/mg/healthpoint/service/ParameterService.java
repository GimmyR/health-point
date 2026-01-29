package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.repository.ParameterRepository;


@Service
@AllArgsConstructor
@Transactional
public class ParameterService {
	
	private ParameterRepository parameterRepository;
	
	public List<Parameter> findAll() {
		
		return parameterRepository.findAll();
		
	}
	
	public Parameter findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Parameter> opt = parameterRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Parameter not found");
		
	}
	
	public Parameter save(Parameter parameter) {
		
		return parameterRepository.save(parameter);
		
	}
	
	public void delete(Parameter parameter) {
		
		parameterRepository.delete(parameter);
		
	}

}
