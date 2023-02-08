package com.khalilmohamed.patternrecognition.repository;

import com.khalilmohamed.patternrecognition.repository.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point,Long> {
}
