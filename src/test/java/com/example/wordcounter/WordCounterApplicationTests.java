package com.example.wordcounter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class WordCounterApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void applicationContextTests(){
        WordCounterApplication.main(new String[]{});
    }
}
