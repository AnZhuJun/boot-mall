package com.damo.bootmall.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class damoException {
    private static final Logger logger= LoggerFactory.getLogger(damoException.class);

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(RuntimeException e){
        logger.info("damo 500 exception");
        ModelAndView m = new ModelAndView();
        m.addObject("damoException",e.getMessage());
        m.setViewName("error/500");
        return m;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(Exception e){
        logger.info("damo 5xx exception");
        ModelAndView m = new ModelAndView();
        m.addObject("exception",e.getMessage());
        m.setViewName("error/5xx");
        return m;
    }

}
