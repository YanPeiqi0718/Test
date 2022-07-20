package com.thundersoft.mvvm.app.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.thundersoft.mvvm.app.R;
import com.thundersoft.mvvm.app.ui.fragment.RadioFmFragmentDirections;
import com.thundersoft.mvvm.common.lib.constant.MusicConstants;
import com.thundersoft.mvvm.vm.manager.SingleLiveDataManager;
import com.thundersoft.mvvm.vm.viewmodel.ActivityViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityViewModel mViewModel;
    private NavController mNavController;
    private SingleLiveDataManager mSingleLiveDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    @Override
    public boolean onSupportNavigateUp() {
        return mNavController.navigateUp();
    }

    private void initView() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ActivityViewModel.class);
    }

    private void initData() {
        mNavController = Navigation.findNavController(this, R.id.main_fragment);
        mSingleLiveDataManager = SingleLiveDataManager.getInstance();
        mSingleLiveDataManager.getShowFragmentLiveData().observe(this, this::showFragment);
    }

    @Override
    public void onClick(View view) {

    }

    private void showFragment(int type) {
        if (type == MusicConstants.LoadFragmentType.RADIO_AM_FRAGMENT) {
            if (mNavController.getCurrentDestination().getLabel().
                    equals(MusicConstants.FragmentLabel.RADIO_FM_FRAGMENT)) {
                mNavController.navigate(RadioFmFragmentDirections.radioFmFragmentToRadioAmFragmentAction());
            }
        } else if (type == MusicConstants.LoadFragmentType.MUSIC_MAIN_FRAGMENT) {
            if (mNavController.getCurrentDestination().getLabel().
                    equals(MusicConstants.FragmentLabel.RADIO_AM_FRAGMENT)) {
                //mNavController.navigate(RadioAmFragmentDirections.radioAmFragmentToMusicMainFragmentAction());
            }
        } else {
            mNavController.navigateUp();
        }
    }
}