package LabAssistant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class UserResourceAssembler implements ResourceAssembler<user_table, Resource<user_table>> {

	@Override
	public Resource<user_table> toResource(user_table user) {

		return new Resource<>(user,
			linkTo(methodOn(UserTableController.class).one(user.getUsername())).withSelfRel(),
			linkTo(methodOn(UserTableController.class).all()).withRel("users"));
	}
}