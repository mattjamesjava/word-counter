package com.example.wordcounter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WordCounterService {

    private Translator translator;
    List<String> wordsList = new ArrayList<>();

    @Autowired
    public WordCounterService(Translator translator){
        this.translator = translator;
    }

    public String addToWordList(String newWordToAdd)
    {
        wordsList.add(translator.checkSameWord(newWordToAdd));
        return "Success";
    }

    public long countWordOccurence(String checkWord){
        return wordsList.stream().filter(word -> word.equals(translator.checkSameWord(checkWord))).count();
    }
}
