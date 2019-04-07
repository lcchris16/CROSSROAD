package com.community.protectcommunity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import razerdp.basepopup.BasePopupWindow;

public class CanteenChoiceTwoKnowledgePointPopup extends BasePopupWindow{
    private View popupWindow;

    public CanteenChoiceTwoKnowledgePointPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        popupWindow = createPopupById(R.layout.pop_up_window_canteen_choice_two_knowledge_point);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://crossroad-1038b.appspot.com/");
        StorageReference islandRef = storageRef.child("knowledge_point_canteen_comfort.png");
        try {
            final File localFile = File.createTempFile("images", "png");

            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Drawable drawable = Drawable.createFromPath(localFile.getPath());
                    popupWindow.setBackground(drawable);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        }
        catch(Exception e) {
            e.printStackTrace();
        }

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

    //close the pop up window
    public void dismissPopupLayout() {
        popupWindow = null;
        this.dismiss();
        System.gc();
    }
}
