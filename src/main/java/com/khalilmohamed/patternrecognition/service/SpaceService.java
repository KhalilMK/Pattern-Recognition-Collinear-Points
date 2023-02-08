package com.khalilmohamed.patternrecognition.service;

import com.khalilmohamed.patternrecognition.repository.model.Line;
import com.khalilmohamed.patternrecognition.repository.model.Point;

import java.util.List;
import java.util.Set;

public interface SpaceService {
    void addPoint(Point p);

    List<Point> findAllPoints();

    void deleteAllPoints();

    Set<Line> findAllLines(Integer dimension);
}
