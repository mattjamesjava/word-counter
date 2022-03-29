package com.example.wordcounter.controller;

import com.example.wordcounter.model.Word;
import com.example.wordcounter.service.WordCounterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = WordCounterController.class)
class WordCounterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordCounterService wordCounterService;

    @BeforeAll
    private static void setup(){
        Word word1 = Word.builder().word("flower").build();
    }

    @Test
    public void add_word_success() throws Exception {
        String returnValue = "Success";
        Mockito.when(wordCounterService.addToWordList(Mockito.any())).thenReturn(returnValue);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/word/add")
                        .content(asJsonString("flower"))
                        .contentType(MediaType.APPLICATION_JSON)
        )     .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    @Test
    public void add_word_invalid_input_exception() throws Exception{
        Mockito.when(wordCounterService.addToWordList("flower1")).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.post("/word/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void test_count_words_success() throws Exception {
        Long count = 1L;
        wordCounterService.addToWordList("flower");
        Mockito.when(wordCounterService.countWordOccurence("flower")).thenReturn(count);
        mockMvc.perform(MockMvcRequestBuilders.get("/word/count")
                .content(asJsonString("flower"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    public void test_count_same_words_different_languages_success() throws Exception {
        Long count = 3L;
        wordCounterService.addToWordList("flower");
        wordCounterService.addToWordList("flor");
        wordCounterService.addToWordList("blume");

        Mockito.when(wordCounterService.countWordOccurence("flower")).thenReturn(count);
        mockMvc.perform(MockMvcRequestBuilders.get("/word/count")
                .content(asJsonString("flower"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    public static String asJsonString(final Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}