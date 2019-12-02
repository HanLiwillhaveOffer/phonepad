package com.antra.phonepad.combination.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
@XmlRootElement
public class WordResponse {
    public List<String> getResult() {
        return result;
    }
    private String message;
    private List<String> result;

    public String getMessage() {
        return message;
    }



    public WordResponse(String first,List<String> second,List<String> third){
        if(second.size()!=0&&third.size()!=0){
            result = new ArrayList<>();
        }

        for(String s:second){
            for(String t:third){
                String tmp = first+"-"+s+"-"+t;
                result.add(tmp);
            }
        }
        if(result==null){
            message = "No Valid combination";
        }
        else{
            message = "Has Valid Combination";
        }
    }
}
