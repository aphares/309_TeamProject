package LabAssistant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
class ChatResourceAssembler implements ResourceAssembler<chat_table, Resource<chat_table>> {

	@Override
	public Resource<chat_table> toResource(chat_table chat) {

		return new Resource<>(chat,
			linkTo(methodOn(ChatTableController.class).one(chat.getId())).withSelfRel(),
			linkTo(methodOn(ChatTableController.class).all()).withRel("chatData"));
	}
}