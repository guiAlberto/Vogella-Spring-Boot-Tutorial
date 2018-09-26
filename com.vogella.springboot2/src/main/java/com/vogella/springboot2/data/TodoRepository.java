package com.vogella.springboot2.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.vogella.springboot2.domain.Todo;

import reactor.core.publisher.Flux;

public interface TodoRepository extends ReactiveCrudRepository<Todo, Long> {

	Flux<Todo> findBySummaryContainingIgnoreCase(String summary);

}
