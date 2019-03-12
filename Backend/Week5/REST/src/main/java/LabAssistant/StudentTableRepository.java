package LabAssistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTableRepository extends JpaRepository<Student, String> {
	
	Student save (Student UserTable);
	
}