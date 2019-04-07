package com.community.protectcommunity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import razerdp.basepopup.BasePopupWindow;

public class ClassroomPopup extends BasePopupWindow implements View.OnClickListener{
    private Button popupYesButton;
    private Button popupNoButton;
    private View popupWindow;
    ClassroomFragment thisFragment;
    public ClassroomPopup(Context context, ClassroomFragment fragment) {
        super(context);
        thisFragment = fragment;
    }

    // 必须实现，这里返回您的contentView
    // 为了让库更加准确的做出适配，强烈建议使用createPopupById()进行inflate
    @Override
    public View onCreateContentView() {
        popupWindow = createPopupById(R.layout.pop_up_window_classroom_question);
        popupYesButton = (Button)popupWindow.findViewById(R.id.classroom_fragment_choice1);
        popupNoButton = (Button)popupWindow.findViewById(R.id.classroom_fragment_choice2);
        popupYesButton.setOnClickListener(this);
        popupNoButton.setOnClickListener(this);
        return popupWindow;
    }

    // 以下为可选代码（非必须实现）
    // 返回作用于PopupWindow的show和dismiss动画，本库提供了默认的几款动画，这里可以自由实现
    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation(true);
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

    @Override
    public void onClick(View view) {
        Fragment nextFragment;
        FragmentManager fragmentManager = thisFragment.getFragmentManager();
        SharedPreferences sharedPref = thisFragment.getActivity().getSharedPreferences("username_gender_choice",Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPref.edit();
        switch (view.getId()) {
            case R.id.classroom_fragment_choice1:
                nextFragment = new ClassroomChoiceOneFragment();
                fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in,R.animator.slide_in_opp,R.animator.slide_out_opp,
                        R.animator.slide_out).replace(R.id.game_change_area, nextFragment).commit();
                spEditor.putString("question1", "YES");
                spEditor.apply();
                dismissPopupLayout();
                break;
            case R.id.classroom_fragment_choice2:
                nextFragment = new ClassroomChoiceTwoFragment();
                fragmentManager.beginTransaction().setCustomAnimations(R.animator.slide_in,R.animator.slide_in_opp,R.animator.slide_out_opp,
                        R.animator.slide_out).replace(R.id.game_change_area, nextFragment).commit();
                spEditor.putString("question1", "NO");
                spEditor.apply();
                dismissPopupLayout();
                break;
            default:
                break;
        }
    }

    //close the pop up window
    public void dismissPopupLayout() {
        this.dismiss();
    }
}
