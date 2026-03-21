package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.dto.SaveParameterRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.repository.ParameterRepository;
import mg.healthpoint.repository.PatientRepository;


@Service
@AllArgsConstructor
@Transactional
public class ParameterService {
	
	private ParameterRepository parameterRepository;
	private PatientRepository patientRepository;
	
	public List<Parameter> findAll() {
		
		return parameterRepository.findAll();
		
	}
	
	public Parameter findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Parameter> opt = parameterRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Parameter not found");
		
	}
	
	public Parameter save(SaveParameterRequest form) throws NotFoundException, BadRequestException {
		
		Optional<Patient> patient = patientRepository.findById(form.patientId());
		
		if(form.min() != null && form.max() != null && form.min() > form.max())
			throw new BadRequestException("Min value should be lower than max value");
		
		if(patient.isEmpty())
			throw new NotFoundException("Patient not found");
		
		Parameter parameter = null;
		
		if(form.id() != null)
			parameter = this.findUniqueById(form.id());
		
		else parameter = new Parameter();
			
		parameter.editName(form.name());
		parameter.editUnit(form.unit());
		parameter.editMin(form.min());
		parameter.editMax(form.max());
		parameter.editPatient(patient.get());
		
		return parameterRepository.save(parameter);
		
	}
	
	public void delete(Parameter parameter) {
		
		parameterRepository.delete(parameter);
		
	}

}
