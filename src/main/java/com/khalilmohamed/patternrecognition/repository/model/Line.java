package com.khalilmohamed.patternrecognition.repository.model;

import lombok.*;

import java.util.Objects;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Line{
    private Set<Point> pointSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return pointSet.equals(line.pointSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointSet);
    }
}
