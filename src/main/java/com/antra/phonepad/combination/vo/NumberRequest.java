package com.antra.phonepad.combination.vo;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement
public class NumberRequest {

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }

    @Pattern(regexp = "^\\d{3}$")
    private String first;

    @Pattern(regexp = "^\\d{4}$")
    private String second;

    @Pattern(regexp = "^\\d{3}$")
    private String third;

    public NumberRequest(){

    }
    public NumberRequest(String first,String second, String third){
        this.first = first;
        this.second = second;
        this.third = third;
    }
    public String toString(){
        return this.first+this.second+this.third;
    }

}
