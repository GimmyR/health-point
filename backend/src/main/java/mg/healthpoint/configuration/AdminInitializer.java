package mg.healthpoint.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import mg.healthpoint.service.StaffService;

@Configuration
public class AdminInitializer implements CommandLineRunner {
	
	@Autowired
	private StaffService staffService;
	
	@Value("${ADMIN_USERNAME}")
	private String adminUsername;
	
	@Value("${ADMIN_PASSWORD}")
	private String adminPassword;

	@Override
	public void run(String... args) throws Exception {
		
		if(!staffService.adminExists())
			staffService.saveAdmin(adminUsername, adminPassword);
		
	}

}
