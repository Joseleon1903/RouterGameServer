package com.server.game.router.RouterServer.exceptionhandler;

import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.h2.message.DbException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

/**
 * Created by jose de leon on 1/19/2021.
 */
@ControllerAdvice
public class ExceptionhadlerController extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(ExceptionhadlerController.class);

    @ExceptionHandler({DbException.class,JdbcSQLIntegrityConstraintViolationException.class})
    public ResponseEntity<Object> handleExceptionsDatabase() {
        JSONObject jo = new JSONObject();
        Date time = new Date();
        jo.put("timestamp", time.toString());
        jo.put("status", "500");
        jo.put("error", "Internal Server Error");
        jo.put("message", "Unique index or primary key violation");
        return new ResponseEntity(jo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnexpectedException.class)
    public ModelAndView handleMyException(UnexpectedException mex) {
        ModelAndView model = new ModelAndView();
        model.addObject("errCode", mex.getErrCode());
        model.addObject("errMsg", mex.getErrMsg());
        model.setViewName("error/GeneralPageError");
        return model;
    }
}
