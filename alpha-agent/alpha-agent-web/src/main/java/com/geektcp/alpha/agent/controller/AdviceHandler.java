package com.geektcp.alpha.agent.controller;//package com.casstime.webforagent.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.InitBinder;
//
//import javax.servlet.http.HttpServletResponse;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author haiyang.tang on 12.06 006 16:24:07.
// */
//@ControllerAdvice
//@Slf4j
//public class AdviceHandler {
//
////    @ExceptionHandler(RuntimeException.class)
////    public String runtimeException(RuntimeException e) {
////        log.info("test runtimeException!");
//////        log.error("runtimeException",e);
////        return "error";
////    }
//
////    @InitBinder
////    public void initBinder(WebDataBinder binder) {
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
////        dateFormat.setLenient(false);
////        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
////    }
//
//}
