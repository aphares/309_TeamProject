package LabAssistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TATableRepository extends JpaRepository<TA, String> {
	
	TA save (TA UserTable);
	
}