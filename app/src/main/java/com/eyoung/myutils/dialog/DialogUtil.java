package com.eyoung.myutils.dialog;

import android.content.Context;
import android.view.View;

import com.eyoung.myutils.R;

/**
 * Author: created by ghappy on 2018/11/7 12:55
 * <p>
 * Description: 封装一些常用的对话框
 */
public class DialogUtil {

    public static CustomDialog showNormalDialog(Context context, String title, String content, String sure, String cancel) {
        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_normal);
        dialog.setText(R.id.dialog_normal_title,title);
        dialog.setText(R.id.dialog_normal_content,content);
        dialog.setText(R.id.dialog_normal_sure,sure);
        dialog.setText(R.id.dialog_normal_cancel,cancel);
        dialog.setOnclickListener(R.id.dialog_normal_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public static CustomDialog showNormalDialog(Context context, int title, int content, int sure, int cancel) {
        final CustomDialog dialog = new CustomDialog(context, R.layout.dialog_normal);
        dialog.setText(R.id.dialog_normal_title,title);
        dialog.setText(R.id.dialog_normal_content,content);
        dialog.setText(R.id.dialog_normal_sure,sure);
        dialog.setText(R.id.dialog_normal_cancel,cancel);
        dialog.setOnclickListener(R.id.dialog_normal_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
