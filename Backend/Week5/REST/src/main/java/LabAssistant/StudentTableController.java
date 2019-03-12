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
class StudentTableController {
	@Autowired
	
	private final StudentTableRepository repository;
	
	private final StudentResourceAssembler assembler;

	StudentTableController(StudentTableRepository repository, StudentResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<Student>> all() {
		
		List<Resource<Student>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(users,
				linkTo(methodOn(StudentTableController.class).all()).withSelfRel());
	}

	@PostMapping("/students")
	ResponseEntity<?> newUserEntry(@RequestBody Student newUserEntry) throws URISyntaxException {
		Resource<Student> resource = assembler.toResource(repository.save(newUserEntry));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	// Single item

	@GetMapping(path = "/students/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	Resource<Student> one(@PathVariable("username") String username) {

		Student users = repository.findById(username)
			.orElseThrow(() -> new StudentEntryNotFoundException(username));

		return assembler.toResource(users);
	}

	@PutMapping("/students/{username}")
	ResponseEntity<?> replaceUserEntry(@RequestBody Student newUserEntry, @PathVariable("username") String username) throws URISyntaxException {

		Student updatedUser = repository.findById(username)
				.map(user -> {
					user.setUsername(newUserEntry.getUsername());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUserEntry.setUsername(username);
					return repository.save(newUserEntry);
				});

			Resource<Student> resource = assembler.toResource(updatedUser);

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/students/{username}")
	ResponseEntity<?> deleteUserEntry(@PathVariable("username") String username) {
		repository.deleteById(username);

		return ResponseEntity.noContent().build();
	}
}