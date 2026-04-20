package mg.healthpoint.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.dto.ParameterResponse;
import mg.healthpoint.dto.SaveParameterRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.repository.ParameterRepository;

@Service
@AllArgsConstructor
@Transactional
public class ParameterService {
	
	private ParameterRepository parameterRepository;
	private PatientService patientService;
	
	public List<Parameter> findAll() {
		
		return parameterRepository.findAll();
		
	}
	
	public Parameter findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Parameter> opt = parameterRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Parameter not found");
		
	}
	
	public Parameter findUniqueWithEntriesById(Integer id) throws NotFoundException {
		
		Optional<Parameter> opt = parameterRepository.findWithEntriesById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Parameter not found");
		
	}
	
	public Parameter save(SaveParameterRequest form) throws NotFoundException, BadRequestException {
		
		if(form.min() != null && form.max() != null && form.min() > form.max())
			throw new BadRequestException("Min value should be lower than max value");
		
		Patient patient = this.patientService.findUniqueByIdWithoutAccount(form.patientId());
		Parameter parameter = null;
		
		if(form.id() != null)
			parameter = this.findUniqueById(form.id());
		
		else parameter = new Parameter();
			
		parameter.editName(form.name());
		parameter.editUnit(form.unit());
		parameter.editMin(form.min());
		parameter.editMax(form.max());
		parameter.editPatient(patient);
		
		return parameterRepository.save(parameter);
		
	}
	
	public void delete(Parameter parameter) {
		
		parameterRepository.delete(parameter);
		
	}
	
	public ParameterResponse mapToParameterResponseWithoutEntries(Parameter parameter) {
		
		ParameterResponse response = new ParameterResponse(
				parameter.getId(), 
				parameter.getName(), 
				parameter.getUnit(),
				parameter.getMin(),
				parameter.getMax(),
				new ArrayList<>());
		
		return response;
		
	}
	
	public void mapParametersWithEntries(List<Parameter> parameters) throws NotFoundException {
		
		for(Parameter param : parameters) {
			
			Parameter parameter = this.findUniqueWithEntriesById(param.getId());
			param.editDetails(parameter.getDetails());
			
		}
		
	}

}
