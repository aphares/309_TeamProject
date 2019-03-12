package LabAssistant;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
class ChatTableController {
	@Autowired
	
	private final ChatTableRepository repository;
	
	private final ChatResourceAssembler assembler;

	ChatTableController(ChatTableRepository repository,ChatResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping(path = "/chatData", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<chat_table>> all() {
		
		List<Resource<chat_table>> chats = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(chats,
				linkTo(methodOn(ChatTableController.class).all()).withSelfRel());
	}

	@PostMapping("/chatData")
	ResponseEntity<?> newChatEntry(@RequestBody chat_table newChatEntry) throws URISyntaxException {
		Resource<chat_table> resource = assembler.toResource(repository.save(newChatEntry));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	// Single item

	@GetMapping(path = "/chatData/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Resource<chat_table> one(@PathVariable Long id) {

		chat_table chatdata = repository.findById(id)
			.orElseThrow(() -> new ChatEntryNotFoundException(id));

		return assembler.toResource(chatdata);
	}

	@PutMapping("/chatData/{id}")
	ResponseEntity<?> replaceChatEntry(@RequestBody chat_table newChatEntry, @PathVariable Long id) throws URISyntaxException {

		chat_table updatedChat = repository.findById(id)
				.map(chat -> {
					chat.setNetid(newChatEntry.getNetid());
					chat.setMessage(newChatEntry.getMessage());
					chat.setTimestamp(newChatEntry.getTimestamp());
					return repository.save(chat);
				})
				.orElseGet(() -> {
					newChatEntry.setId(id);
					return repository.save(newChatEntry);
				});

			Resource<chat_table> resource = assembler.toResource(updatedChat);

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/chatData/{id}")
	ResponseEntity<?> deleteChatEntry(@PathVariable Long id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}