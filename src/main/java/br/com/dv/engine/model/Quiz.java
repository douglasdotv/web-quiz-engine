package br.com.dv.engine.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Quiz {

    private Integer id;

    private String title;

    private String text;

    private List<String> options;

    private Set<Integer> answerIndices;

}
