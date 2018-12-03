package com.eyoung.myutils.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.eyoung.myutils.R;

/**
 * Author: created by ghappy on 2018/10/15 14:24
 * <p>
 * Description:
 */
public class LoadingProgress extends ProgressDialog {
    public LoadingProgress(Context context) {
        super(context);
    }

    public LoadingProgress(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_progress_dialog);
    }

    public static LoadingProgress show(Context context) {
        LoadingProgress dialog = new LoadingProgress(context, R.style.loadingProgress);
        dialog.show();
        return dialog;
    }
}
