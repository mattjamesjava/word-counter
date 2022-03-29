package com.example.wordcounter.controller;

import com.example.wordcounter.model.Word;
import com.example.wordcounter.service.WordCounterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/word")
public class WordCounterController {

    private WordCounterService wordCounterService;

    @Autowired
    public WordCounterController(WordCounterService wordCounterService){
        this.wordCounterService = wordCounterService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    private String addWord(@Valid @RequestBody Word addWord){
        log.debug("New word request: {}",addWord);
        return wordCounterService.addToWordList(addWord.getWord());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/count")
    private long countWordOccurence(@Valid @RequestBody Word countWord){
        log.debug("Count Word Occurence: {}",countWord);
        return wordCounterService.countWordOccurence(countWord.getWord());
    }
}
