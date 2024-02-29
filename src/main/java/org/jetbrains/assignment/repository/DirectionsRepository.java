package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.repository.entities.DirectionRequest;
import org.springframework.data.repository.CrudRepository;

public interface DirectionsRepository extends CrudRepository<DirectionRequest, String> {
}
