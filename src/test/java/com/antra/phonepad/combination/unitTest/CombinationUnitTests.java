package com.antra.phonepad.combination.unitTest;

import com.antra.phonepad.combination.util.WordSet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombinationUnitTests {

    public boolean checkWord(String word){
        try {
            String fileName = "words.txt";
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            Stream<String> lines = Files.lines(path);
            List<String> data = lines.collect(Collectors.toList());
            lines.close();
            for(String cur:data){
                if(cur.contains(word)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
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
