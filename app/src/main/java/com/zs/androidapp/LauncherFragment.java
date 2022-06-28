package com.zs.androidapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;

/**
 * fragment 5种状态以及对应的回调方法
 * 1、INITIALIZED：正在创建对应视图
 * 2、CREATED(onViewStateRestored、onCreateView、onViewCreated、onCreate、onStop、onDestroyView)：视图创建完成
 * 3、STARTED(onStart)：可见不可交互
 * 4、RESUMED(onResume)：可见可交互
 * 5、DESTROYED(onDestroy)
 */

/**
 * fragment之间传递固定值
 * 1、在接收值的fragment设置listener(parentFragmentManager.setFragmentResultListener("key", this, listener))
 *      当fragment处于STARTED状态时，监听器就会工作
 * 2、在发送数据的fragment中pareFragmentManager.setFragmentResult("key", bundle)
 * 对于给定的键，只能有一个监听器和结果。如果您对同一个键多次调用setFragmentResult(), 并且监听器未处于STARTED状态,
 *      新的值会覆盖旧值
 */

/**
 * 当需要给每个fragment添加顶部应用栏的时候，使用ToolBar，不要使用setSupportActionBar(),
 *      actionBar只适用于activity
 */

class LauncherFragment extends Fragment {

    private FragmentManager mChildFragmentManager;

    public LauncherFragment(){
        super(R.layout.launcher_fragment);
    }

    @Override
    public void onAttach(@NonNull @android.support.annotation.NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this,
                new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull @android.support.annotation.NonNull String requestKey,
                                         @NonNull @android.support.annotation.NonNull Bundle result) {
                String resultStr = result.getString(requestKey);
            }
        });
    }

    @Nullable
    @android.support.annotation.Nullable
    @Override
    public View onCreateView(@NonNull @android.support.annotation.NonNull LayoutInflater inflater,
                             @Nullable @android.support.annotation.Nullable ViewGroup container,
                             @Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull @android.support.annotation.NonNull View view, @Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChildFragmentManager = getChildFragmentManager();
        int tag = requireArguments().getInt("tag");
    }

    //视图创建完成？
    @Override
    public void onViewStateRestored(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull @android.support.annotation.NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
