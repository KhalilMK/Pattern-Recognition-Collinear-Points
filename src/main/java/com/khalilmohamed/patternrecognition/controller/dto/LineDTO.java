package com.khalilmohamed.patternrecognition.controller.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class LineDTO implements Serializable {

    private Set<PointDTO> pointSet;
}
