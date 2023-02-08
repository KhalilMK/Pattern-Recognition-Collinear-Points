package com.khalilmohamed.patternrecognition.controller;

import com.khalilmohamed.patternrecognition.controller.dto.LineDTO;
import com.khalilmohamed.patternrecognition.controller.dto.PointDTO;
import com.khalilmohamed.patternrecognition.repository.model.Point;
import com.khalilmohamed.patternrecognition.service.SpaceService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;


@RestController
public class SpaceController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SpaceService spaceService;

    private final Logger LOGGER =
            LoggerFactory.getLogger(SpaceController.class);

    @PostMapping(value = "/point", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PointDTO> addPoint(@RequestBody PointDTO pointDTO){
        LOGGER.info("SpaceController.addPoint method - enter");
        Point p = modelMapper.map(pointDTO,Point.class);

        spaceService.addPoint(p);

        LOGGER.info("SpaceController.addPoint method - exit");
        return new ResponseEntity<>(pointDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/space", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PointDTO>> findAllPoints(){
        LOGGER.info("SpaceController.findAllPoints method - enter");
        List<PointDTO> pointDTOS;
        Type listType = new TypeToken<List<PointDTO>>(){}.getType();

        pointDTOS = modelMapper.map(spaceService.findAllPoints(), listType);

        LOGGER.info("SpaceController.findAllPoints method - exit");
        return new ResponseEntity<>(pointDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/lines/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<LineDTO>> findAllLines(@PathVariable(name = "n") Integer dimension){
        LOGGER.info("SpaceController.findAllLines method - enter");
        Set<LineDTO> lineDTOS;
        Type listType = new TypeToken<Set<LineDTO>>(){}.getType();

        lineDTOS = modelMapper.map(spaceService.findAllLines(dimension), listType);

        LOGGER.info("SpaceController.findAllLines method - exit");
        return new ResponseEntity<>(lineDTOS, HttpStatus.OK);
    }

    @DeleteMapping(value = "/space")
    public ResponseEntity deleteAllPoints(){
        LOGGER.info("SpaceController.deleteAllPoints method - enter");

        spaceService.deleteAllPoints();

        LOGGER.info("SpaceController.deleteAllPoints method - exit");
        return new ResponseEntity(HttpStatus.OK);
    }
}
