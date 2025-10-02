package com.senai.flora;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FloraIntegrationTest { //TODO Implement the tests for the Flora application

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void mustRegisterValidFlower(){
    }


    @Test
    void mustNotRegisterInvalidFlower() throws Exception{

    }


    @Test
    void mustUpdateSuccessfullyFlower(){
    }


    @Test
    void mustNotUpdateSuccessfullyFlower() throws Exception{
    }


    @Test
    void mustDisableSuccessfullyFlower(){
    }

    @Test
    void mustNotDisableSuccessfullyFlower() throws Exception{
    }

    @Test
    void mustGetFlower(){
    }

    @Test
    void mustGetListOfFlowers(){
    }

    @Test
    void mustNotGetFlower() throws Exception{
    }
}
