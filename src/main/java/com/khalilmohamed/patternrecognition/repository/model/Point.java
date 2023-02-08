package com.khalilmohamed.patternrecognition.repository.model;

import lombok.*;

import javax.persistence.*;
import java.util.Comparator;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@IdClass(PointPK.class)
public class Point implements Comparable<Point>{
    @Id
    private double x;
    @Id
    private double y;

    @Transient
    public Comparator<Point> SLOPE_COMPARATOR = new ComparatorSlope();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double slope(Point p2){
        int compare = this.compareTo(p2);

        if(compare == 0)
            return Double.NEGATIVE_INFINITY;

        if(this.x == p2.x)
            return Double.POSITIVE_INFINITY;

        return (p2.y - this.y)/(p2.x - this.x);
    }

    @Override
    public int compareTo(Point p2) {
        if(this.equals(p2))
            return 0;

        if(this.y < p2.y || this.y == p2.y && this.x < p2.x)
            return -1;
        else
            return +1;
    }

    private class ComparatorSlope implements Comparator<Point> {
        public int compare(Point p, Point q) {
            double slope1 = p.slope(Point.this);
            double slope2 = q.slope(Point.this);

            return Double.compare(slope1, slope2);
        }
    }
}
