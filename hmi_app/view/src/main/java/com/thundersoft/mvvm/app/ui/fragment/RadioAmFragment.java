package com.thundersoft.mvvm.app.ui.fragment;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Observer;

import com.thundersoft.mvvm.app.R;
import com.thundersoft.mvvm.app.base.BaseFragment;
import com.thundersoft.mvvm.common.lib.constant.MusicConstants;
import com.thundersoft.mvvm.repository.utils.LogUtil;
import com.thundersoft.mvvm.vm.DemoViewModel;

public class RadioAmFragment extends BaseFragment<DemoViewModel> implements View.OnClickListener {

    private static final String TAG = RadioAmFragment.class.getSimpleName();
    private Button mBackBtn;
    private Button mLoadMusicBtn;
    private DemoViewModel mViewModel;

    @Override
    public int initLayout() {
        return R.layout.layout_radio_am;
    }

    @Override
    public void initView(View view) {
        mBackBtn = (Button) view.findViewById(R.id.radio_am_back_button);
        mLoadMusicBtn = (Button) view.findViewById(R.id.radio_am_changed_button);
    }

    @Override
    public void initData() {
        mViewModel = getViewModel();
        mBackBtn.setOnClickListener(this);
        mLoadMusicBtn.setOnClickListener(this);
    }

    @Override
    public void initViewObservable() {
        mViewModel.getMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                LogUtil.logD(TAG,"message:" + message);
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.radio_am_changed_button) {
            //mViewModel.changedFragment(MusicConstants.LoadFragmentType.MUSIC_MAIN_FRAGMENT);
            mViewModel.requestMessage(1);
        } else if (view.getId() == R.id.radio_am_back_button) {
            mViewModel.changedFragment(MusicConstants.LoadFragmentType.RADIO_FM_FRAGMENT);
        } else {

        }
    }
}
