package com.zs.androidapp;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public class MainActivity extends FragmentActivity {

    public static final String TAG = "MainActivity";
    private String mApplicationId;
    private FragmentManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //设置自定义的生成fragment工厂类，必须放在activity的super.onCreate()之前，以确保可以正常运行
        mManager = getSupportFragmentManager();
        //mManager.setFragmentFactory(new LauncherFragmentFactory(0));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            addFragment();
        }

        mApplicationId = BuildConfig.APPLICATION_ID;
    }


    //activity添加顶部应用栏
    private void addActionBar(){
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.actionbar_layout);
            actionBar.setHomeButtonEnabled(true);//使actionbar上的按钮响应点击事件
            actionBar.setDisplayHomeAsUpEnabled(true);//添加左上角返回按钮
            actionBar.setDisplayShowCustomEnabled(true);//使setCustomView生效
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //处理按钮操作
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    //添加menu菜单，
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.launcher_menu, menu);
        return true;
    }

    //监听menu的点击事件
    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull @android.support.annotation.NonNull MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     *Fragment事务管理器FragmentManager：
     * 1、在每一层访问事务管理器的方法：
     *      a、在activity中getSupportFragmentManager()获取此层的fragmentManager
     *      b、在第一层fragment中getChildFragmentManager()获取此层的fragmentManager
     *      c、可以通过getParentFragmentManager()获取上一层的fragmentManager
     *
     * 2、当对事务调用addToBackStack()时，事务会被添加到回退栈，当调用popBackStack()会对相应的事务进行反转。
     * 3、如果在执行移除fragment的事务时没有调用addToBackStack()，提交事务之后被移除的fragment会被销毁无法在返回到fragment；
     *      反之，则fragment只会进入STOPPED不会被销毁，当返回时进入RESUMED此时视图会被销毁重新创建。
     * 4、可以对每个事务设置主要导航的一个fragment,setPrimaryNavigationFragment(new LauncherFragment())。
     * 5、当发生返回事件时，返回事件从最内层开始，一旦最内层没有可以弹回的fragment事务，则返回事件交给上一层处理直到activity。
     *
     */

    /**
     * fragment创建工厂FragmentFactory:
     * 当系统配置信息发生改变的时候，activity以及所有的fragment都会销毁并重新创建，FragmentManager会重新创建fragment实例，
     *      并将事务添加到回退栈。默认情况下，FragmentManager使用框架提供的FragmentFactory实例化Fragment的新实例。
     *      此默认工厂使用反射来查找和调用Fragment的无参数构造函数。这就意味着如果需要有参的构造方法生成的实例，我们就需要
     *      重写FragmentFactory(如：LauncherFragmentFactory)，随后通过FragmentManager设置自定义的工厂类
     *
     */
    public void addFragment(){
        Bundle bundle = new Bundle();
        bundle.putInt("tag", 0);
        mManager.beginTransaction()
                //设置导航fragment
                .setPrimaryNavigationFragment(new LauncherFragment())
                .setReorderingAllowed(true) //优化事务中涉及到的状态变化，以是动画和过度正常
                .add(R.id.launcher_app_fragment, LauncherFragment.class, bundle)
                //.add(R.id.text_app_fragment, TextFragment.class, bundle)
                .addToBackStack("launcher") //添加到回退栈，name可以为null
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //activity在任务堆栈中被复用，回调此方法传回intent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //处理物理返回按钮
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

/**
 * 当activity A启动Activity B时生命周期变化
 * 1、Activity A onPause()
 * 2、activity B onCreate()、onStart()、onResume()
 * 3、activity A onStop()
 */

/**
 * activity或者对话框显示在前台
 * 1、新的activity或者对话框显示在前台, 并且部分覆盖正在运行的activity，则onPause()
 * 2、如果是完全覆盖，则onPause-->onStop
 * 3、当被覆盖的activity是同一实例返回前台，则onRestart-->onStart-->onResume
 *    新实例返回前台，则onStart-->onResume
 */

/**
 * activity启动模式
 * 1、standard：如果activity指定此模式，系统会将其从任务栈移除，并在其原来的位置创建一个新实例
 * 2、singleTop：如果当前任务的顶部已经存在该任务，则会通过回调onNewIntent复用当前的activity
 * 3、singleTask：系统在没有和所要启动的activity具有相同亲和性的时候会创建新任务，并实例化新任务的根activity（此时点击返回按钮仍然返回启动它的activity），
 *      如果已经存在相同亲和性的任务存在，则会启动到该任务栈中
 *      如果在其他任务中已经存在该activity实例，则会复用该activity并将该activity所在的任务栈放到前台，覆盖当前的
 *      任务堆栈
 * 4、singleInstance: 和singTask相似，唯一不同的是系统不会将任何其他activity实例放到该任务栈中
 * */

/**
 * TransactionTooLargeException异常：
 *      Binder事务缓冲区的大小固定有限，目前为1MB，由进程中正在处理的所有事务共享。由于此限制是进程级别而不是Activity级别的限制，
 *      因此这些事务包括应用中的所有binder事务，例如onSaveInstanceState，startActivity以及与系统的任何互动。超过大小限制时，
 *      将引发TransactionTooLargeException。
 * 在android 7.0（api 24）或更高版本中，当超过大小限制时，系统会抛出此异常，在低版本中系统只会在logcat中显示警告
 */
