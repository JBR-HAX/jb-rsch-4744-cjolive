package org.jetbrains.assignment.service;

import org.jetbrains.assignment.model.Coordinate;
import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.repository.CoordinateRepository;
import org.jetbrains.assignment.repository.DirectionsRepository;
import org.jetbrains.assignment.repository.entities.CoordinateResponse;
import org.jetbrains.assignment.repository.entities.DirectionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RobotService {

    private DirectionsRepository directionsRepository;
    private CoordinateRepository coordinateRepository;

    @Autowired
    public RobotService(DirectionsRepository directionsRepository, CoordinateRepository coordinateRepository) {
        this.directionsRepository = directionsRepository;
        this.coordinateRepository = coordinateRepository;
    }

    public List<Coordinate> performDirections(List<Direction> directions) {
        String requestId = UUID.randomUUID().toString();
        DirectionRequest request = new DirectionRequest();
        request.setRequestId(requestId);

        String directionString = directions.stream().map(d -> d.toString()).collect(Collectors.joining(" "));
        request.setDirections(directionString);
        directionsRepository.save(request);

        Coordinate initial = new Coordinate(0,0);
        List<Coordinate> results = new ArrayList<>();
        results.add(initial);

        for (Direction d: directions) {
            initial = performMove(initial, d);
            results.add(initial);
        }

        CoordinateResponse r = new CoordinateResponse();
        r.setRequestId(requestId);

        String coordinateString = results.stream().map(c -> c.toString()).collect(Collectors.joining(" "));
        r.setCoordinates(coordinateString);

        coordinateRepository.save(r);
        return results;
    }

    public List<Direction> performCoordinates(List<Coordinate> coordinates) {

        List<Direction> results = new ArrayList<>();
        for (int i = 0; i < coordinates.size() -1; i++) {
            Direction d = performMove(coordinates.get(i),coordinates.get(i+1));
            results.add(d);
        }

        return results;
    }

    private Coordinate performMove(Coordinate from, Direction direction) {

        Coordinate to = new Coordinate(from.getX(), from.getY());

        // diagram has Y as x axis giving different result - setting logic so X is horizonal axis
        switch (direction.getDirection()) {
            case "NORTH":
                to.addY(direction.getSteps());
                break;
            case "EAST":
                to.addX(direction.getSteps());
                break;
            case "SOUTH":
                to.subtractY(direction.getSteps());
                break;
            case "WEST":
                to.subtractX(direction.getSteps());
                break;
        }

        return to;
    }

    private Direction performMove(Coordinate from, Coordinate to) {

        if (to.getX() != from.getX()) {
            int diff = to.getX() - from.getX();
            if (diff > 0) {
                return new Direction("EAST", diff);
            }

            if (diff < 0) {
                return new Direction("WEST", Math.abs(diff));
            }
        }

        if (to.getY() != from.getY()) {
            int diff = to.getY() - from.getY();
            if (diff > 0) {
                return new Direction("NORTH", diff);
            }

            if (diff < 0) {
                return new Direction("SOUTH", Math.abs(diff));
            }
        }

        throw new IllegalArgumentException("x or y must change");
    }

}
