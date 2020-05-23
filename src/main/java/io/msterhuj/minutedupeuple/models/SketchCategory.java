package io.msterhuj.minutedupeuple.models;

import lombok.*;

import java.io.File;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SketchCategory {

    private int id;
    private String name;
    private File folder;
    private List<Sketch> sketches;

}
