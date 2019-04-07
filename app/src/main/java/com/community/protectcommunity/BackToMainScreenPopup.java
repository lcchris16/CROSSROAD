package com.community.protectcommunity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;

import razerdp.basepopup.BasePopupWindow;

public class BackToMainScreenPopup extends BasePopupWindow implements View.OnClickListener{
    private Button popupYesButton;
    private Button popupNoButton;
    private View popupWindow;
    private Activity activity;
    public BackToMainScreenPopup(Context context, Activity activity) {
        super(context);
        this.activity = activity;
    }

    // 必须实现，这里返回您的contentView
    // 为了让库更加准确的做出适配，强烈建议使用createPopupById()进行inflate
    @Override
    public View onCreateContentView() {
        popupWindow = createPopupById(R.layout.pop_up_window_back_to_mainscreen);
        popupYesButton = (Button)popupWindow.findViewById(R.id.back_to_mainscreen_yes_button);
        popupNoButton = (Button)popupWindow.findViewById(R.id.back_to_mainscreen_no_button);
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
        switch (view.getId()) {
            case R.id.back_to_mainscreen_yes_button:
                dismissPopupLayout();
                getHome();
                System.gc();
                break;
            case R.id.back_to_mainscreen_no_button:
                dismissPopupLayout();
                break;
            default:
                break;
        }
    }


    //close the pop up window
    public void dismissPopupLayout() {
        System.gc();
        this.dismiss();
    }


    //Go back to main page method
    public void getHome(){
        Intent intent = new Intent(this.getContext(), MainScreenActivity.class);
        this.getContext().startActivity(intent);
        activity.finish();
        //this.getContentView()
    }


}
