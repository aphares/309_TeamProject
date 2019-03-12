package LabAssistant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class TAResourceAssembler implements ResourceAssembler<TA, Resource<TA>> {

	@Override
	public Resource<TA> toResource(TA user) {

		return new Resource<>(user,
			linkTo(methodOn(TATableController.class).one(user.getUsername())).withSelfRel(),
			linkTo(methodOn(TATableController.class).all()).withRel("TA"));
	}
}