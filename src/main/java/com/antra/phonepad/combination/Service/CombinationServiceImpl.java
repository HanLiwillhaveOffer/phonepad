package com.antra.phonepad.combination.Service;


import com.antra.phonepad.combination.util.Mapper;
import com.antra.phonepad.combination.util.WordSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("combinationService")
public class CombinationServiceImpl implements CombinationService {
    @Autowired
    Mapper map;

    @Autowired
    WordSet wordSet;

    Map<String,List<String>> memo = new HashMap<>();
    public List<String> getCombination(String number){
        if(memo.containsKey(number)||dfs(0,number,"")){
            return memo.get(number);
        }
        return new ArrayList<>();
    }

    public boolean dfs(int index,String number,String tmp){
        if(index>=number.length()){
            if(isWord(tmp)){
                List<String> cur = memo.getOrDefault(number,new ArrayList<>());
                cur.add(tmp);
                memo.put(number,cur);
                return true;
            }
            else{
                return false;
            }
        }
        boolean flag = false;
        char cur = number.charAt(index);
        String candidate = map.getPhone_pad()[Character.getNumericValue(cur)];
        if(!candidate.equals("")) {
            for (char next : candidate.toCharArray()) {
                String newString = tmp + next;
                if(dfs(index+1,number,newString)){
                    flag = true;
                }
            }
        }
        else{
            flag = dfs(index+1,number,tmp);
        }
        return flag;
    }

    public boolean isWord(String word){
        return wordSet.getWordSet().contains(word);
    }




}
