package com.antra.phonepad.combination.Controller;

import com.antra.phonepad.combination.Service.CombinationService;
import com.antra.phonepad.combination.exception.InvalidPhoneNumberException;
import com.antra.phonepad.combination.vo.ErrorResponse;
import com.antra.phonepad.combination.vo.NumberRequest;
import com.antra.phonepad.combination.vo.WordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/phonePad")

public class CombinationController {
    private static Logger logger = LoggerFactory.getLogger(CombinationController.class);

    CombinationService combinationService;
    @Autowired
    public CombinationController(CombinationService combService){
        this.combinationService = combService;
    }

    @RequestMapping(value ="/combination",method = RequestMethod.POST)
    public ResponseEntity<WordResponse> getCombination(@Valid @RequestBody NumberRequest number, BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new InvalidPhoneNumberException("This is not a valid phone number.");
        }
        System.out.println(number.toString());
        List<String> second  = combinationService.getCombination(number.getSecond());
        List<String> third = combinationService.getCombination(number.getThird());
        return new ResponseEntity<WordResponse>(new WordResponse(number.getFirst(),second,third), HttpStatus.OK);
    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public ResponseEntity<String> get(){
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerInvalidPhoneNumber(Exception e){
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
