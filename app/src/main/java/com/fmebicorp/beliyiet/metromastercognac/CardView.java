package com.fmebicorp.beliyiet.metromastercognac;

/**
 * Created by BELIYIET on 2017/5/28.
 */

public class CardView {
    private String name;
    private int imageId;

    public CardView(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
