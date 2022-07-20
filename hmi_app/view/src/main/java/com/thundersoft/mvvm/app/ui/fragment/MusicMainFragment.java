package com.thundersoft.mvvm.app.ui.fragment;

import android.view.View;
import android.widget.RadioButton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.thundersoft.mvvm.app.R;
import com.thundersoft.mvvm.app.base.BaseFragment;

public class MusicMainFragment extends BaseFragment implements View.OnClickListener {

    private RadioButton mBTMusicBtn;
    private RadioButton mUsbMusicBtn;
    private RadioButton mOnlineMusicBtn;

    private NavController mNavController;

    @Override
    public int initLayout() {
        return R.layout.layout_music;
    }

    @Override
    public void initView(View view) {
        mBTMusicBtn = (RadioButton) view.findViewById(R.id.music_tab_bar_bt_music_radio_button);
        mUsbMusicBtn = (RadioButton) view.findViewById(R.id.music_tab_bar_usb_music_radio_button);
        mOnlineMusicBtn = (RadioButton) view.findViewById(R.id.music_tab_bar_online_music_radio_button);

        mBTMusicBtn.setOnClickListener(this);
        mUsbMusicBtn.setOnClickListener(this);
        mOnlineMusicBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mNavController = Navigation.findNavController(requireActivity(), R.id.music_fragment);
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.music_tab_bar_bt_music_radio_button) {
            mNavController.setGraph(R.navigation.graph_bt_music_fragment);
        } else if (view.getId() == R.id.music_tab_bar_usb_music_radio_button) {
            mNavController.setGraph(R.navigation.graph_usb_music_fragment);
        } else if (view.getId() == R.id.music_tab_bar_online_music_radio_button) {
            mNavController.setGraph(R.navigation.graph_online_music_graph);
        } else {

        }
    }
}
