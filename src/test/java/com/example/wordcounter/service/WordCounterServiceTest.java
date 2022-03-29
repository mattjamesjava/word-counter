package com.example.wordcounter.service;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WordCounterServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private Translator translator = new Translator();

    @InjectMocks
    private WordCounterService wordCounterService = new WordCounterService(translator);

    @Test
    public void test_add_word(){
        String expectedResponse = "Success";
        String actualResponse = wordCounterService.addToWordList("flower");
        assertEquals(expectedResponse,actualResponse);
    }

    @Test
    public void test_count_words(){
        wordCounterService.addToWordList("flower");
        wordCounterService.addToWordList("blume");
        wordCounterService.addToWordList("flor");
        Long count = wordCounterService.countWordOccurence("flower");
        assertEquals(3,count);
    }

    @Test
    public void test_count_word_not_in_list(){
        wordCounterService.addToWordList("rose");
        Long count = wordCounterService.countWordOccurence("flower");
        assertEquals(0,count);
    }
}