package LabAssistant;

class ChatEntryNotFoundException extends RuntimeException {

	ChatEntryNotFoundException(Long id) {
		super("Could not find ChatEntry " + id);
	}
}