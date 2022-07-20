package com.thundersoft.mvvm.app.ui.fragment;

import android.view.View;
import android.widget.Button;

import com.thundersoft.mvvm.app.R;
import com.thundersoft.mvvm.app.base.BaseFragment;
import com.thundersoft.mvvm.common.lib.constant.MusicConstants;
import com.thundersoft.mvvm.vm.viewmodel.RadioFmViewModel;

public class RadioFmFragment extends BaseFragment<RadioFmViewModel> implements View.OnClickListener {

    private Button mLoadAmBtn;
    private RadioFmViewModel mViewModel;

    @Override
    public int initLayout() {
        return R.layout.layout_radio_fm;
    }

    @Override
    public void initView(View view) {
        mLoadAmBtn = (Button) view.findViewById(R.id.radio_fm_changed_button);
    }

    @Override
    public void initData() {
        mViewModel = getViewModel();
        mLoadAmBtn.setOnClickListener(this);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.radio_fm_changed_button) {
            mViewModel.changedFragment(MusicConstants.LoadFragmentType.RADIO_AM_FRAGMENT);
        }
    }
}
