package com.app.academia.classes.components;

import android.content.Context;
import android.widget.CheckBox;

import java.util.List;

public class CheckboxGroup {
    List<CheckBox> checkBoxes;
    Context context;

    public CheckboxGroup(List<CheckBox> checkBoxes, boolean status) {
        this.checkBoxes = checkBoxes;
        setGroupEnabled(status);
    }

    public void setGroupEnabled(boolean status) {
        for (CheckBox checkbox : checkBoxes) {
            checkbox.setEnabled(status);
        }
    }
}
