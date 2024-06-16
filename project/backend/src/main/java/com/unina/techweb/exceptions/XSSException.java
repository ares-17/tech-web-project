package com.unina.techweb.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class XSSException extends TechWebException{

    public XSSException(){
        super(HttpStatus.BAD_GATEWAY, "Founded XSS attack at ".concat(new Date().toString()), "Founded a XSS attack");
    }
}
