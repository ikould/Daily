package com.ikould.daily.set.output;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ikould.daily.R;
import com.ikould.frame.fragment.BaseFragment;

/**
 * describe
 * Created by ikould on 2017/6/20.
 */

public class SetFragment extends BaseFragment {

    @Override
    protected void onBaseCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setBaseContentView(R.layout.fragment_set);
    }
}
