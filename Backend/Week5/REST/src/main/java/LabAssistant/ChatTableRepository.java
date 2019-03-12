package LabAssistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatTableRepository extends JpaRepository<chat_table, Long> {
	
	chat_table save (chat_table ChatTable);
	
}