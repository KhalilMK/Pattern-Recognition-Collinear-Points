package com.khalilmohamed.patternrecognition.service.implementation;

import com.khalilmohamed.patternrecognition.repository.PointRepository;
import com.khalilmohamed.patternrecognition.repository.model.Line;
import com.khalilmohamed.patternrecognition.repository.model.Point;
import com.khalilmohamed.patternrecognition.service.SpaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private PointRepository pointRepository;

    private final Logger LOGGER =
            LoggerFactory.getLogger(SpaceServiceImpl.class);

    @Override
    public void addPoint(Point p) {
        LOGGER.info("SpaceServiceImpl.addPoint method - enter");
        if(p != null)
            pointRepository.save(p);
        LOGGER.info("SpaceServiceImpl.addPoint method - exit");
    }

    @Override
    public List<Point> findAllPoints() {
        LOGGER.info("SpaceServiceImpl.findAllPoints method - enter");
        Sort sort = Sort.by(Sort.Direction.ASC, "y", "x");
        LOGGER.info("SpaceServiceImpl.findAllPoints method - exit");
        return pointRepository.findAll(sort);
    }

    @Override
    public void deleteAllPoints() {
        LOGGER.info("SpaceServiceImpl.deleteAllPoints method - enter");
        pointRepository.deleteAll();
        LOGGER.info("SpaceServiceImpl.deleteAllPoints method - exit");
    }

    @Override
    public Set<Line> findAllLines(Integer dimension) {
        LOGGER.info("SpaceServiceImpl.findAllLines method - enter");
        Set<Line> lines = new HashSet<>();
        Point[] pointsToSort = findAllPoints().toArray(new Point[0]);
        List<Point> points =  findAllPoints();

        if(points.size() < dimension)
            return lines;

        for(Point p1 : points){
            Arrays.sort(pointsToSort, p1.SLOPE_COMPARATOR);
            double firstSlope = p1.slope(pointsToSort[1]);
            Set<Point> pointSet = new TreeSet<>();
            pointSet.add(p1);
            pointSet.add(pointsToSort[1]);

            for(int i = 2; i < pointsToSort.length; i++) {
                double currentSlope = p1.slope(pointsToSort[i]);
                if (currentSlope == firstSlope) {
                    pointSet.add(pointsToSort[i]);
                } else {
                    if(pointSet.size() >= dimension)
                        lines.add(new Line(pointSet));
                    pointSet = new TreeSet<>();
                    pointSet.add(p1);
                    pointSet.add(pointsToSort[i]);
                    firstSlope = currentSlope;
                }
            }
        }
        LOGGER.info("SpaceServiceImpl.findAllLines method - exit");
        return lines;
    }
}
