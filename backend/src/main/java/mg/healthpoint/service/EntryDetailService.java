package mg.healthpoint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import mg.healthpoint.exception.NotFoundException;
import mg.healthpoint.entity.EntryDetail;
import mg.healthpoint.repository.EntryDetailRepository;


@Service
@AllArgsConstructor
@Transactional
public class EntryDetailService {
	
	private EntryDetailRepository entryDetailRepository;
	
	public List<EntryDetail> findAll() {
		
		return entryDetailRepository.findAll();
		
	}
	
	public EntryDetail findUniqueById(Integer id) throws NotFoundException {
		
		Optional<EntryDetail> opt = entryDetailRepository.findById(id);
		
		if(opt.isPresent())
			return opt.get();
		
		else throw new NotFoundException("Entry Detail not found");
		
	}
	
	public EntryDetail save(EntryDetail entryDetail) {
		
		return entryDetailRepository.save(entryDetail);
		
	}
	
	public void delete(EntryDetail entryDetail) {
		
		entryDetailRepository.delete(entryDetail);
		
	}

}
