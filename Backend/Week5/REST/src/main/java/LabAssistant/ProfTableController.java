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
class ProfTableController {
	@Autowired
	
	private final ProfTableRepository repository;
	
	private final ProfResourceAssembler assembler;

	ProfTableController(ProfTableRepository repository, ProfResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root

	@GetMapping(path = "/professors", produces = MediaType.APPLICATION_JSON_VALUE)
	Resources<Resource<Prof>> all() {
		
		List<Resource<Prof>> users = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList());

			return new Resources<>(users,
				linkTo(methodOn(ProfTableController.class).all()).withSelfRel());
	}

	@PostMapping("/professors")
	ResponseEntity<?> newUserEntry(@RequestBody Prof newUserEntry) throws URISyntaxException {
		Resource<Prof> resource = assembler.toResource(repository.save(newUserEntry));

		return ResponseEntity
			.created(new URI(resource.getId().expand().getHref()))
			.body(resource);
	}

	// Single item

	@GetMapping(path = "/professors/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	Resource<Prof> one(@PathVariable("username") String id) {

		Prof users = repository.findById(id)
			.orElseThrow(() -> new ProfEntryNotFoundException(id));

		return assembler.toResource(users);
	}

	@PutMapping("/professors/{username}")
	ResponseEntity<?> replaceUserEntry(@RequestBody Prof newUserEntry, @PathVariable("username") String id) throws URISyntaxException {

		Prof updatedUser = repository.findById(id)
				.map(user -> {
					user.setUsername(newUserEntry.getUsername());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUserEntry.setUsername(id);
					return repository.save(newUserEntry);
				});

			Resource<Prof> resource = assembler.toResource(updatedUser);

			return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}

	@DeleteMapping("/professors/{username}")
	ResponseEntity<?> deleteUserEntry(@PathVariable("username") String id) {
		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
}