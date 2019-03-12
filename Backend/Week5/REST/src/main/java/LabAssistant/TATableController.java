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
class TATableController {
	@Autowired
	
	private final TATableRepository repository;
	
	private final TAResourceAssembler assembler;

	TATableController(TATableRepository repository, TAResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping(path = "/tas", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<TA>> all() {
		
		List<Resource<TA>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(users,
				linkTo(methodOn(TATableController.class).all()).withSelfRel());
	}

	@PostMapping("/tas")
	ResponseEntity<?> newUserEntry(@RequestBody TA newUserEntry) throws URISyntaxException {
		Resource<TA> resource = assembler.toResource(repository.save(newUserEntry));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	// Single item

	@GetMapping(path = "/tas/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	Resource<TA> one(@PathVariable("username") String string) {

		TA users = repository.findById(string)
			.orElseThrow(() -> new TAEntryNotFoundException(string));

		return assembler.toResource(users);
	}

	@PutMapping("/tas/{username}")
	ResponseEntity<?> replaceUserEntry(@RequestBody TA newUserEntry, @PathVariable("username") String id) throws URISyntaxException {

		TA updatedUser = repository.findById(id)
				.map(user -> {
					user.setUsername(newUserEntry.getUsername());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUserEntry.setUsername(id);
					return repository.save(newUserEntry);
				});

			Resource<TA> resource = assembler.toResource(updatedUser);

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/tas/{username}")
	ResponseEntity<?> deleteUserEntry(@PathVariable("username") String id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}