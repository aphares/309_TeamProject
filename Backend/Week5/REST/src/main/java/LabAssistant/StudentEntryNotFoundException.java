package LabAssistant;

class StudentEntryNotFoundException extends RuntimeException {

	StudentEntryNotFoundException(String id) {
		super("Could not find StudentEntry " + id);
	}
}