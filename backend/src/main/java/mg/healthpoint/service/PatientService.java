package mg.healthpoint.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.dto.AccountResponse;
import mg.healthpoint.dto.ParameterEntryResponse;
import mg.healthpoint.dto.ParameterResponse;
import mg.healthpoint.dto.PatientItemResponse;
import mg.healthpoint.dto.PatientResponse;
import mg.healthpoint.dto.SavePatientRequest;
import mg.healthpoint.entity.Parameter;
import mg.healthpoint.entity.Patient;
import mg.healthpoint.repository.ParameterRepository;
import mg.healthpoint.repository.PatientRepository;

@Service
@AllArgsConstructor
@Transactional
public class PatientService {
	
	private PatientRepository patientRepository;
	private ParameterRepository parameterRepository;
	
	public List<Patient> findAllWithAccount() {
		
		return patientRepository.findAllWithAccount();
		
	}
	
	public Patient findUniqueById(Integer id) throws NotFoundException {
		
		Optional<Patient> opt = patientRepository.findWithAccountById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Patient not found");
		
	}
	
	public Patient findUniqueByUsername(String username) throws NotFoundException {
		
		Optional<Patient> opt = patientRepository.findWithParametersByUsername(username);
		
		if(opt.isEmpty())
			throw new NotFoundException("Patient not found");
		
		Patient result = opt.get();
		
		result.getParameters().forEach(param -> { 
			this.mapParametersWithEntries(param); 
		});
		
		return result;
		
	}
	
	private void mapParametersWithEntries(Parameter param) {
		
		Optional<Parameter> optParam = parameterRepository.findWithEntriesById(param.getId());
		
		if(optParam.isEmpty())
			System.out.println(String.format("Parameter (ID: %d) not found", param.getId()));
		
		else param.editDetails(optParam.get().getDetails());
		
	}
	
	public Patient findUniqueWithParametersById(Integer id) throws NotFoundException {
		
		Optional<Patient> opt = patientRepository.findWithParametersById(id);
		
		if(opt.isEmpty())
			throw new NotFoundException("Patient not found");
		
		Patient result = opt.get();
		
		result.getParameters().forEach(param -> { 
			this.mapParametersWithEntries(param); 
		});
		
		return result;
		
	}
	
	public boolean isPatient(String username) {
		
		Optional<Patient> opt = patientRepository.findByAccountUsername(username);
		return opt.isPresent();
		
	}
	
	public PatientResponse mapToPatientResponse(Patient patient, List<LocalDateTime> entryDates) {
		
		List<ParameterResponse> parameters = new ArrayList<>();
		
		patient.getParameters().forEach(p -> {
			List<ParameterEntryResponse> entries = new ArrayList<>();
			
			p.getDetails().forEach(d -> {
				entries.add(new ParameterEntryResponse(d.getId(), d.getEntryDate(), d.getValue()));
			});
			
			parameters.add(new ParameterResponse(p.getId(), p.getName(), p.getUnit(), p.getMin(), p.getMax(), entries));
		});
		
		return new PatientResponse(
				patient.getId(),
				patient.getRoom(),
				patient.getDiagnosis(),
				new AccountResponse(
						patient.getAccount().getFirstname(),
						patient.getAccount().getLastname(),
						patient.getAccount().getGender(),
						patient.getAccount().getDateOfBirth(),
						patient.getAccount().getAddress()),
				parameters, 
				entryDates
		);
		
	}
	
	public List<PatientItemResponse> mapToListOfPatientItemResponse(List<Patient> patients) {
		
		List<PatientItemResponse> results = new ArrayList<PatientItemResponse>();
		
		patients.forEach(patient -> {
			results.add(new PatientItemResponse(
					patient.getId(), 
					patient.getAccount().getFirstname(), 
					patient.getAccount().getLastname(), 
					patient.getAccount().getGender(), 
					patient.getRoom())
			);
		});
		
		return results;
		
	}
	
	public PatientResponse mapToPatientResponseWithoutParametersAndEntryDates(Patient patient) {
		
		return new PatientResponse(
				patient.getId(),
				patient.getRoom(),
				patient.getDiagnosis(),
				new AccountResponse(
						patient.getAccount().getFirstname(),
						patient.getAccount().getLastname(),
						patient.getAccount().getGender(),
						patient.getAccount().getDateOfBirth(),
						patient.getAccount().getAddress()),
				null, 
				null
		);
		
	}
	
	public Patient save(SavePatientRequest form) throws NotFoundException {
		
		Patient patient = this.findUniqueById(form.id());
		patient.editRoom(form.room());
		patient.editDiagnosis(form.diagnosis());
		patient.getAccount().editFirstname(form.account().firstname());
		patient.getAccount().editLastname(form.account().lastname());
		patient.getAccount().editGender(form.account().gender());
		patient.getAccount().editDateOfBirth(form.account().dateOfBirth());
		patient.getAccount().editAddress(form.account().address());
		
		return patientRepository.save(patient);
		
	}
	
	public void delete(Patient patient) {
		
		patientRepository.delete(patient);
		
	}

}
