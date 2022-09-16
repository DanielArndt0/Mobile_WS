package com.app.academia.classes.tests;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Check {
    private final TextInputLayout text;
    private final boolean valid;
    private final String message;

    public Check(TextInputLayout text, boolean valid, String message) {
        this.text = text;
        this.valid = valid;
        this.message = message;
    }

    public TextInputLayout getText() {
        return text;
    }

    public boolean isInvalid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
