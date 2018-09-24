package com.vogella.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
class TodoRestController {

	private Flux<Todo> todos;

	public TodoRestController() {
		todos = createTodoModel();
	}

	private Flux<Todo> createTodoModel() {
		Todo todo = new Todo(1);
		todo.setSummary("Learn Spring Boot 2.0");
		todo.setDescription("Easily create modern reactive webapps with Spring Boot 2.0");

		Todo todo2 = new Todo(2);
		todo2.setSummary("Learn Reactor Framework");
		todo2.setDescription("Use the power of the reactive io api with the Reactor Framework");

		Todo todo3 = new Todo(3);
		todo3.setSummary("Learn @RestController");
		todo3.setDescription("Learn how to create @RestController and use rest endpoints");

		return Flux.just(todo, todo2, todo3);
	}

	@GetMapping("/getTodos")
	public Flux<Todo> getTodos(@RequestParam(name = "limit", required = false, defaultValue = "-1") long limit) {
		if (limit == -1) {
			return todos;
		}
		return todos.take(limit);
	}

	@GetMapping("/getTodoById")
	public Mono<Todo> getTodoById(long id) {
		return Mono.from(todos.filter(t -> id == t.getId()));
	}

}