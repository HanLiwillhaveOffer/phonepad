package com.antra.phonepad.combination.Controller;

import com.antra.phonepad.combination.Service.CombinationService;
import com.antra.phonepad.combination.vo.NumberRequest;
import com.antra.phonepad.combination.vo.WordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/phonepad")

public class CombinationController {
    private static Logger logger = LoggerFactory.getLogger(CombinationController.class);

    CombinationService combinationService;
    @Autowired
    public CombinationController(CombinationService combService){
        this.combinationService = combService;
    }

    @RequestMapping(value ="/combination",method = RequestMethod.POST)
    public ResponseEntity<WordResponse> getCombination(@RequestBody NumberRequest number) throws Exception{
        System.out.println(number.toString());
        List<String> first  = combinationService.getCombination(number.getFirst());
        List<String> second  = combinationService.getCombination(number.getSecond());
        List<String> third = combinationService.getCombination(number.getThird());
        return new ResponseEntity<WordResponse>(new WordResponse(first,second,third), HttpStatus.OK);
    }
}
