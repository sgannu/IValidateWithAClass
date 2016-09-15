package com.sgannu.ivalidator.example;


import org.apache.commons.lang3.StringUtils;

import com.sgannu.ivalidator.AbstractValidator;
import com.sgannu.ivalidator.Validates;

public class NameValidator extends AbstractValidator<String> {

    private static final int MAX_LENGTH = 15;
    static final String INVALID_FIRST_NAME_ERRORCODE = "ERRCODE183";

    public NameValidator() {
        super(Validates.map().empty(INVALID_FIRST_NAME_ERRORCODE).invalidLength(INVALID_FIRST_NAME_ERRORCODE));
    }

    @Override
    public boolean invalidLength(String target) {
        return StringUtils.length(target) > MAX_LENGTH;
    }
}
