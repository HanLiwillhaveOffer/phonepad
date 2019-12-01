package com.antra.phonepad.combination.Service;


import java.util.List;

public interface CombinationService {

    List<String> getCombination(String number);
    boolean dfs(int index,String number,String tmp);
    boolean isWord(String word);
}
