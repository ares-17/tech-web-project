package com.unina.techweb.validators;

import com.unina.techweb.exceptions.XSSException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {

    @Override
    public void initialize(NoHtml noHtml) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        var isValid = Jsoup.isValid(value, Safelist.none());

        if(!isValid){
            throw new XSSException();
        }

        return true;
    }
}

