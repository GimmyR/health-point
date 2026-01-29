package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.repository.PatientRepository;


@Service
@AllArgsConstructor
@Transactional
public class PatientService {
	
	private PatientRepository patientRepository;
	
	public List<Patient> findAll() {
		
		return patientRepository.findAll();
		
	}
	
	public Patient findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Patient> opt = patientRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Patient not found");
		
	}
	
	public Patient save(Patient patient) {
		
		return patientRepository.save(patient);
		
	}
	
	public void delete(Patient patient) {
		
		patientRepository.delete(patient);
		
	}

}
