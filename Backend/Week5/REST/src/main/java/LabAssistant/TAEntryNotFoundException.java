package LabAssistant;

class TAEntryNotFoundException extends RuntimeException {

	TAEntryNotFoundException(String id) {
		super("Could not find TA Entry " + id);
	}
}