package com.example.wordcounter.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @NotNull
    @Pattern( regexp = "^[a-zA-Z]*$",message = "Not a valid word")
    private String word;
}
