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

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.animation.AccelerateDecelerateInterpolator;

public abstract class AnimatorAdapter {
    static final int ANIMATION_DURATION = 400;

    protected CardStackView mCardStackView;
    protected AnimatorSet mSet;

    public AnimatorAdapter(CardStackView cardStackView) {
        mCardStackView = cardStackView;
    }

    protected void initAnimatorSet() {
        mSet = new AnimatorSet();
        mSet.setInterpolator(new AccelerateDecelerateInterpolator());
        mSet.setDuration(getDuration());
    }

    public void itemClick(final CardStackView.ViewHolder viewHolder, int position) {
        if (mSet != null && mSet.isRunning()) return;
        initAnimatorSet();
        if (mCardStackView.getSelectPosition() == position) {
            onItemCollapse(viewHolder);
        } else {
            onItemExpand(viewHolder, position);
        }
        if (mCardStackView.getChildCount() == 1)
            mSet.end();
    }

    protected abstract void itemExpandAnimatorSet(CardStackView.ViewHolder viewHolder, int position);

    protected abstract void itemCollapseAnimatorSet(CardStackView.ViewHolder viewHolder);

    private void onItemExpand(final CardStackView.ViewHolder viewHolder, int position) {
        final int preSelectPosition = mCardStackView.getSelectPosition();
        final CardStackView.ViewHolder preSelectViewHolder = mCardStackView.getViewHolder(preSelectPosition);
        if (preSelectViewHolder != null) {
            preSelectViewHolder.onItemExpand(false);
        }
        mCardStackView.setSelectPosition(position);
        itemExpandAnimatorSet(viewHolder, position);
        mSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mCardStackView.setScrollEnable(false);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                viewHolder.onItemExpand(true);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if (preSelectViewHolder != null) {
                    preSelectViewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, false);
                }
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, true);
            }
        });
        mSet.start();
    }

    private void onItemCollapse(final CardStackView.ViewHolder viewHolder) {
        itemCollapseAnimatorSet(viewHolder);
        mSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                viewHolder.onItemExpand(false);
                mCardStackView.setScrollEnable(true);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_START, false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mCardStackView.setSelectPosition(CardStackView.DEFAULT_SELECT_POSITION);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_END, false);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                viewHolder.onAnimationStateChange(CardStackView.ANIMATION_STATE_CANCEL, false);
            }
        });
        mSet.start();
    }

    protected int getCollapseStartTop(int collapseShowItemCount) {
        return mCardStackView.getOverlapGapsCollapse()
                * (mCardStackView.getNumBottomShow() - collapseShowItemCount - (mCardStackView.getNumBottomShow() - (mCardStackView.getChildCount() - mCardStackView.getSelectPosition() > mCardStackView.getNumBottomShow()
                ? mCardStackView.getNumBottomShow()
                : mCardStackView.getChildCount() - mCardStackView.getSelectPosition() - 1)));
    }

    public int getDuration() {
        return mCardStackView.getDuration();
    }
}
