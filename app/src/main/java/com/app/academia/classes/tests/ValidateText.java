package com.app.academia.classes.tests;

import java.util.List;

public class ValidateText {
    private List<Check> checkList;

    public boolean execute(List<Check> checkList) {
        this.checkList = checkList;
        boolean notError = true;
        for (Check check : checkList) {
            if (check.isInvalid()) {
                check.getText().setError(check.getMessage());
                notError = false;
            }
        }
        return notError;
    }

    public void clean() {
        if (checkList != null) {
            for (Check check : checkList) {
                check.getText().setErrorEnabled(false);
            }
        }
    }
}
