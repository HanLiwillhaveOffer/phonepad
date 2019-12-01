package com.antra.phonepad.combination.util;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Mapper {
    public String[] getPhone_pad() {
        return phonePad;
    }
    private String[] phonePad;
    public Mapper(){
        this.phonePad = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    }

}
