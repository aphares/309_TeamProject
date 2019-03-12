package LabAssistant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class ProfResourceAssembler implements ResourceAssembler<Prof, Resource<Prof>> {

	@Override
	public Resource<Prof> toResource(Prof user) {

		return new Resource<>(user,
			linkTo(methodOn(ProfTableController.class).one(user.getUsername())).withSelfRel(),
			linkTo(methodOn(ProfTableController.class).all()).withRel("Prof"));
	}
}