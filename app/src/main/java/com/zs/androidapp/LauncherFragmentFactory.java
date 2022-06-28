package com.zs.androidapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;


class LauncherFragmentFactory extends FragmentFactory {
    private int mTag;

    public LauncherFragmentFactory(int tag){
        mTag = tag;
    }

    @NonNull
    @android.support.annotation.NonNull
    @Override
    public Fragment instantiate(@NonNull @android.support.annotation.NonNull ClassLoader classLoader,
                                @NonNull @android.support.annotation.NonNull String className) {
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);
        if (fragmentClass == LauncherFragment.class){
            return new LauncherFragment();
        }
        return super.instantiate(classLoader, className);
    }
}
