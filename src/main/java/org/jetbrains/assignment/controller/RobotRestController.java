package org.jetbrains.assignment.controller;

import org.jetbrains.assignment.model.Coordinate;
import org.jetbrains.assignment.model.Direction;
import org.jetbrains.assignment.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RobotRestController {

    final RobotService robotService;

    @Autowired
    public RobotRestController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/locations")
    public ResponseEntity<List<Coordinate>> performDirections (@RequestBody List<Direction> directions) {

        List<Coordinate> coordinates = robotService.performDirections(directions);

        return ResponseEntity.ok(coordinates);
    }

    @PostMapping("/moves")
    public ResponseEntity<List<Direction>> performCoordinates (@RequestBody List<Coordinate> coordinates) {

        List<Direction> directions = robotService.performCoordinates(coordinates);

        return ResponseEntity.ok(directions);
    }

}
