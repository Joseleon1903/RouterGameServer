package com.server.game.router.RouterServer.config;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jose eduardo on 10/6/2020.
 */
public class AppUtilContext {

    public static String getBaseUrl(){
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        String contextUrl =  req.getContextPath()+req.getPathInfo();
        return  contextUrl;
    }


}
