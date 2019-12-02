package com.antra.phonepad.combination.unitTest;

import com.antra.phonepad.combination.Controller.CombinationController;
import com.antra.phonepad.combination.Service.CombinationService;
import com.antra.phonepad.combination.util.WordSet;
import com.antra.phonepad.combination.vo.NumberRequest;
import io.restassured.mapper.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Matchers.anyString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CombinationUnitTests {

    @Autowired
    CombinationService combinationService;

    @Before
    public void configMock(){
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(new CombinationController(combinationService));
    }

    @Test
    public void testGetCombination(){
        NumberRequest  testNumber = new NumberRequest("217","8198","210");
        Mockito.when(combinationService.getCombination(anyString())).thenReturn(new ArrayList<>());
        given().accept("application/json").contentType("application/json").body(testNumber).post("/phonePad/combination").peek().
                then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo(new ArrayList()));
    }

    @Test
    public void testWordSet(){
        WordSet ws = new WordSet();
        Assert.assertTrue(ws.getWordSet().contains("cake"));
        Assert.assertTrue(ws.getWordSet().contains("hello"));
        Assert.assertTrue(ws.getWordSet().contains("big"));
        Assert.assertFalse(ws.getWordSet().contains("csqrd"));
    }
}
