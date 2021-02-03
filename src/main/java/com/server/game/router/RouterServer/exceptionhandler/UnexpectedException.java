package com.server.game.router.RouterServer.exceptionhandler;

import org.springframework.stereotype.Component;

/**
 * Created by jose de leon on 1/31/2021.
 */
@Component
public class UnexpectedException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public UnexpectedException() { }

    public UnexpectedException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
