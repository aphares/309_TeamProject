package LabAssistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<user_table, String> {
	
	user_table save (user_table UserTable);
	
}