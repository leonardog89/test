package com.leo.test.Models.Entry;


import com.leo.test.Presentation.Base.BaseModel;

public class EntryPrice extends BaseModel {

    private String label;
    private Atribute attributes;

    public EntryPrice() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Atribute getAttributes() {
        return attributes;
    }

    public void setAttributes(Atribute attributes) {
        this.attributes = attributes;
    }
}
