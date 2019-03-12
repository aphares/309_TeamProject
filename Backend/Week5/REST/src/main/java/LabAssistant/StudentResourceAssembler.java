package LabAssistant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class StudentResourceAssembler implements ResourceAssembler<Student, Resource<Student>> {

	@Override
	public Resource<Student> toResource(Student user) {

		return new Resource<>(user,
			linkTo(methodOn(StudentTableController.class).one(user.getUsername())).withSelfRel(),
			linkTo(methodOn(StudentTableController.class).all()).withRel("students"));
	}
}