package com.khalilmohamed.patternrecognition.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PointDTO implements Serializable {
    private double x;
    private double y;
}
