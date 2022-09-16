package com.app.academia.classes.tests;

import android.widget.CheckBox;

import java.util.List;

public class CheckboxGroup {
    private final List<CheckBox> checkBoxes;

    public CheckboxGroup(List<CheckBox> checkBoxes, boolean groupEnabled) {
        this.checkBoxes = checkBoxes;
        setGroupEnable(groupEnabled);
    }

    public void setGroupEnable(boolean status) {
        for (CheckBox checkBox: checkBoxes) {
            checkBox.setEnabled(status);
        }
    }
}
