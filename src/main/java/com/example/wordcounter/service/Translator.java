package com.example.wordcounter.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class Translator {

    Map<String, String> map;

    Translator(){
        map = new HashMap<>();
        map.put("flor", "flower");
        map.put("blume", "flower");
        map.put("flower", "flower");
    }

    public String checkSameWord(String word){
        if(map.containsKey(word)){
            String englishWord = map.get(word);
            return englishWord;
        }else{
            return word;
        }
    }
}
