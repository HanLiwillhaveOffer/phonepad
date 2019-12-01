package com.antra.phonepad.combination.vo;

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

    private String first,second,third;

    public String toString(){
        return this.first+this.second+this.third;
    }

}
