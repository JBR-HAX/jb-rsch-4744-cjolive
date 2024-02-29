package org.jetbrains.assignment.repository;

import org.jetbrains.assignment.repository.entities.CoordinateResponse;
import org.springframework.data.repository.CrudRepository;

public interface CoordinateRepository extends CrudRepository<CoordinateResponse, String> {
}
