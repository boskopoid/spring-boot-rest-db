package com.boskopoid.simpleservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ForMockitoTest {

    @Mock
    private BookRepository helloRepository;

//    @InjectMocks // auto inject helloRepository
//    private HelloService helloService = new HelloServiceImpl();

    @BeforeEach
    void setMockOutput() {
//        when(helloRepository.get()).thenReturn("Hello Mockito From Repository");
        List<Book> myList = Arrays.asList(new Book("One"), new Book("TWO"), new Book("three"));
        when(helloRepository.findByName("Name")).thenReturn(myList);
    }

//    @DisplayName("Test Mock helloService + helloRepository")
    @Test
    void testGet() {
        List<Book> myBookLIst = helloRepository.findByName("Name");

        assertEquals("TWO", myBookLIst.get(1).getName());
    }

}
