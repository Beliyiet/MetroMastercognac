/*******************************************************************************
 * Copyright © 2017 FMEBI.Corp System and CreateON Studio. All rights reserved.
 * Before using all the features of Metro Master (hereinafter referred to as MeM), please be sure to read and thoroughly understand this statement. You may choose not to use MeM, but if you use it, your use will be deemed to be a recognition of the entire contents of this statement.
 * Disclaimer: In view of MeM is currently not fully developed in the function, and the contents of the data information is limited by the information collected in the collection, processing errors may occur, the data is not entirely collected by the CreateON Studio, so the system of search / analysis The results are not responsible. The system does not assume any liability for any adverse consequences arising from the retrieval / analysis of the system as a basis for any commercial conduct or academic research.
 * About privacy: MeM does not currently collect personal privacy other than geographic location information about the user during use.
 * About copyright:
 * 1. All works of MeM that indicate "Metro Master", "Metro Master", "CreateON", "CreateON Studio", "" are owned by CreateON Studio and MeM. Other media, companies, organizations, websites or individuals may not be reproduced in any form, nor distorted and tampered with the contents of the system.
 * 2.MeM currently only granted to the Shanghai Fire Brigade special detachment of the new Jing squadron all, temporarily not granted any other units and personal use.
 * 3. Units authorized by the system shall not exceed the scope of authorization.
 * 4. The information provided by the system does not conform to the relevant paper text, subject to the paper text.
 * 5. If you are in contact with MeM due to the contents of the work, copyright and other issues, please do so within 30 days after MeM has released the work.
 * The right to interpret the system: the declaration of the system and its modification, renewal and final interpretation are owned by CreateON Studio and MeM.
 ******************************************************************************/

package com.loopeer.cardstack;

import android.view.View;

public class StackScrollDelegateImpl implements ScrollDelegate{

    private CardStackView mCardStackView;
    private int mScrollY;
    private int mScrollX;

    public StackScrollDelegateImpl(CardStackView cardStackView) {
        mCardStackView = cardStackView;
    }

    private void updateChildPos() {
        for (int i = 0; i < mCardStackView.getChildCount(); i++) {
            View view = mCardStackView.getChildAt(i);
            if (view.getTop() - mScrollY < mCardStackView.getChildAt(0).getY()) {
                view.setTranslationY(mCardStackView.getChildAt(0).getY() - view.getTop());
            } else if (view.getTop() - mScrollY > view.getTop()) {
                view.setTranslationY(0);
            } else {
                view.setTranslationY(-mScrollY);
            }
        }
    }

    @Override
    public void scrollViewTo(int x, int y) {
        x = clamp(x, mCardStackView.getWidth() - mCardStackView.getPaddingRight() - mCardStackView.getPaddingLeft(), mCardStackView.getWidth());
        y = clamp(y, mCardStackView.getShowHeight(), mCardStackView.getTotalLength());
        mScrollY = y;
        mScrollX = x;
        updateChildPos();
    }

    private static int clamp(int n, int my, int child) {
        if (my >= child || n < 0) {
            return 0;
        }
        if ((my + n) > child) {
            return child - my;
        }
        return n;
    }

    @Override
    public void setViewScrollY(int y) {
        scrollViewTo(mScrollX, y);
    }

    @Override
    public void setViewScrollX(int x) {
        scrollViewTo(x, mScrollY);
    }

    @Override
    public int getViewScrollY() {
        return mScrollY;
    }

    @Override
    public int getViewScrollX() {
        return mScrollX;
    }
}
