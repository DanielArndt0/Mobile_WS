package com.app.academia.classes.validations;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Check {
    private final TextInputLayout textBox;
    private final boolean toBeVerified;
    private final String message;

    public Check(TextInputLayout textBox, boolean toBeVerified, String message) {
        this.textBox = textBox;
        this.toBeVerified = toBeVerified;
        this.message = message;
    }

    public TextInputLayout getTextBox() {
        return textBox;
    }

    public boolean isToBeVerified() {
        return toBeVerified;
    }

    public String getMessage() {
        return message;
    }
}
