package com.app.academia.classes.validations;

import java.util.List;

public class FieldCheck {
    private List<Check> checkList;

    public boolean execute(List<Check> checkList) {
        this.checkList = checkList;
        boolean fError = true;
        for (Check check : checkList) {
            if (check.isToBeVerified()) {
                check.getTextBox().setError(check.getMessage());
                fError = false;
            }
        }
        return fError;
    }

    public void clear() {
        if (checkList != null) {
            for(Check check : checkList) {
                check.getTextBox().setErrorEnabled(false);
            }
        }
    }
}
