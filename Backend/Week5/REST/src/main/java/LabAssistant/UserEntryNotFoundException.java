package LabAssistant;

class UserEntryNotFoundException extends RuntimeException {

	UserEntryNotFoundException(String username) {
		super("Could not find UserEntry " + username);
	}
}