package com.antra.phonepad.combination.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class WordSet {
    public Set<String> getWordSet() {
        return wordSet;
    }
    private Set<String> wordSet;
    public WordSet(){
        try{
            String fileName = "words.txt";
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            Stream<String> lines = Files.lines(path);
            List<String> data = lines.collect(Collectors.toList());
            lines.close();
            wordSet = new HashSet<>(data);
        }
        catch (IOException | URISyntaxException | NullPointerException e){
            e.printStackTrace();
        }
    }

}
