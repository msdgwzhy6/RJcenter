package com.rsen.angcyo;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;

import com.angcyo.rsen.R;
import com.rsen.base.RBaseDialogFragment;
import com.rsen.util.ResUtil;

/**
 * 装载进度对话框
 * <p/>
 * Created by angcyo on 16-01-31-031.
 */
public class LoadDialogFragment extends RBaseDialogFragment {
    public static final String KEY_TIP = "tip";
    public static final String KEY_DIM = "dim";
    public static final String KEY_CANCEL = "cancel";
    private String tip;
    private boolean isDim = true;
    private boolean canCancel = false;

    public static void launch(@NonNull FragmentManager fragmentManager, String tip) {
        launch(fragmentManager, tip, false, true);
    }

    public static void launch(@NonNull FragmentManager fragmentManager, String tip, boolean isDim, boolean canCancel) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TIP, tip);
        bundle.putBoolean(KEY_DIM, isDim);
        bundle.putBoolean(KEY_CANCEL, canCancel);
        LoadDialogFragment fragment = new LoadDialogFragment();
        fragment.setArguments(bundle);
        fragment.show(fragmentManager, LoadDialogFragment.class.getSimpleName());
    }

    @Override
    protected void initArguments(Bundle arguments) {
        super.initArguments(arguments);
        if (arguments != null) {
            tip = arguments.getString(KEY_TIP);
            isDim = arguments.getBoolean(KEY_DIM, isDim);
            canCancel = arguments.getBoolean(KEY_CANCEL, canCancel);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (TextUtils.isEmpty(tip)) {
            mViewHolder.v(R.id.load_tip).setVisibility(View.GONE);
        } else {
            mViewHolder.tV(R.id.load_tip).setText(tip);
        }
        initBgDrawable();//设置圆角背景
    }

    @Override
    protected int getContentView() {
        return R.layout.rsen_load_dialog_fragment;
    }

    @Override
    protected int getWindowWidth() {
        return -2;
    }

    @Override
    protected boolean canCancelable() {
        return canCancel;
    }

    @Override
    protected int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    protected boolean isDimEnabled() {
        return isDim;
    }

    private void initBgDrawable() {
        float round = ResUtil.dpToPx(getResources(), 5);
        RoundRectShape rectShape = new RoundRectShape(new float[]{round, round, round, round, round, round, round, round}, null, null);
        ShapeDrawable bgDrawable = new ShapeDrawable(rectShape);
        bgDrawable.getPaint().setColor(Color.parseColor("#40000000"));
        ResUtil.setBgDrawable(mViewHolder.v(R.id.load_layout), bgDrawable);
    }
}
