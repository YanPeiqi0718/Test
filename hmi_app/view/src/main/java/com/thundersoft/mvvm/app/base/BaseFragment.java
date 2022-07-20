package com.thundersoft.mvvm.app.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.thundersoft.mvvm.vm.base.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    private final static String TAG = BaseFragment.class.getSimpleName();

    protected VM mViewModel;

    public abstract int initLayout();

    public abstract void initView(View view);

    public abstract void initData();

    public abstract void initViewObservable();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "BaseFragment onActivityCreated!");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragment onCreateView!");
        View view = inflater.inflate(initLayout(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "BaseFragment onViewCreated!");
        initViewModel();
        initData();
        initViewObservable();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d(TAG, "BaseFragment onAttachFragment!");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "BaseFragment onDetach!");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "BaseFragment onPause!");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "BaseFragment onDestroyView!");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "BaseFragment onDestroy!");
    }

    public <T extends ViewModel> VM getViewModel() {
        return mViewModel;
    }

    /**
     * Create ViewModel
     */
    private <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(cls);
    }

    private void initViewModel() {
        if (mViewModel == null) {
            Class vmClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                vmClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                vmClass = BaseViewModel.class;
            }
            mViewModel = (VM) createViewModel(getActivity(), vmClass);
        }
    }
}