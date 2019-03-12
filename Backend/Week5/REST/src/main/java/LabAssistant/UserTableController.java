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
class UserTableController {
	@Autowired
	
	private final UserTableRepository repository;
	
	private final UserResourceAssembler assembler;

	UserTableController(UserTableRepository repository, UserResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<user_table>> all() {
		
		List<Resource<user_table>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(users,
				linkTo(methodOn(UserTableController.class).all()).withSelfRel());
	}
	
	@GetMapping(path = "/utas", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<user_table>> unionTA() {
		// Return a RESOURCE of user_table and ta join on username
		List<Resource<user_table>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(users,
				linkTo(methodOn(UserTableController.class).all()).withSelfRel());
	}

	@PostMapping("/users")
	ResponseEntity<?> newUserEntry(@RequestBody user_table newUserEntry) throws URISyntaxException {
		Resource<user_table> resource = assembler.toResource(repository.save(newUserEntry));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	// Single item

	@GetMapping(path = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	Resource<user_table> one(@PathVariable("username") String id) {

		user_table users = repository.findById(id)
			.orElseThrow(() -> new UserEntryNotFoundException(id));

		return assembler.toResource(users);
	}

	@PutMapping("/users/{username}")
	ResponseEntity<?> replaceUserEntry(@RequestBody user_table newUserEntry, @PathVariable("username") String id) throws URISyntaxException {

		user_table updatedUser = repository.findById(id)
				.map(user -> {
					user.setUsername(newUserEntry.getUsername());
					user.setPassword(newUserEntry.getPassword());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUserEntry.setUsername(id);
					return repository.save(newUserEntry);
				});

			Resource<user_table> resource = assembler.toResource(updatedUser);

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/users/{username}")
	ResponseEntity<?> deleteUserEntry(@PathVariable("username") String id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}