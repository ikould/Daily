package com.ikould.frame.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikould.frame.utils.KeyBoardUtils;

/**
 * 基础Fragment
 * <p>
 * Created by ikould on 2017/5/31.
 */
public abstract class BaseFragment extends Fragment {

    public View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        onBaseCreateView(inflater, container, savedInstanceState);
        return contentView;
    }

    protected abstract void onBaseCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 获取ContentView
     *
     * @return
     */
    public void setBaseContentView(@LayoutRes int layoutId) {
        contentView = LayoutInflater.from(getActivity()).inflate(layoutId, null);
    }

    /**
     * 获取ContentView
     *
     * @return
     */
    public void setBaseContentView(View view) {
        contentView = view;
    }

    /**
     * 更换Fragment
     */
    public void replaceFragment(int id, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (tag == null) {
            fragmentTransaction.replace(id, fragment);
        } else {
            fragmentTransaction.replace(id, fragment, tag);
            fragmentTransaction.addToBackStack(tag);
        }
        KeyBoardUtils.closeKeyboard(getActivity());
        fragmentTransaction.commitAllowingStateLoss();
    }
}
