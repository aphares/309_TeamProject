package LabAssistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfTableRepository extends JpaRepository<Prof, String> {
	
	Prof save (Prof UserTable);
	
}