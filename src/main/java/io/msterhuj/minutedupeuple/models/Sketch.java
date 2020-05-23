package io.msterhuj.minutedupeuple.models;

import lombok.*;

import java.io.File;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sketch {

    private int id;
    private String name;
    File sketch;

}
