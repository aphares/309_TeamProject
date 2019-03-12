package LabAssistant;

class ProfEntryNotFoundException extends RuntimeException {

	ProfEntryNotFoundException(String id) {
		super("Could not find professor Entry " + id);
	}
}