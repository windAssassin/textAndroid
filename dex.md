android 中的java代码在编译后生成class文件，在打包成apk文件时被dx命令优化成Android虚拟机可执行的dex文件，
dex文件比较紧凑，这使得程序在Android平台上运行的比较快。

Android gradle可以通过dexOptions{}闭包对dex命令进行设置：

dexOptions{
    //用来配置是否启用dx的增量模式，默认不启用，增量模式速度更快，但是限制比较多
    incremental true  
    //配置执行dx命令的最大堆内存
    javaMaxHeapSize '4g'
    //是否开启jumbo模式，当项目函数数量超过65535的时候，开启此模式项目才会构建成功 
    jumboMode true
    //是否预执行dex libraries库工程，开启后会大大提高构建的速度，但是会影响clean的构建速度，默认开启
    //当需要使用dx的--multi-dex生成多个dex时，导致和库工程有冲突，需要将该设置变为false
    //是不是在Android 5之前启用MultiDex的情况下此属性改为false？
    preDexLibraries true
    //gradle运行dx时使用的线程，适当的线程数量可以提高dx的效率
    threadCount 2
}


当我们在项目中大量引用第三方库的时候，在开发的过程中可能会遇到如下错误：
    Conversion to Dalvik format failed
    Unable to execute dex: method ID not in [0, 0xffff]: 65535
    或者
    trouble writing output
    too many filed references: 131000; max is 65535
    You may try using --multi-dex option
出现这种错误说明，整个项目的函数超过了65535的限制

解决办法：
    1、在Android 5之后，使用了ART的运行时方式，可以天然支持app有多个dex文件，ART在安装运行app的时候
        执行预编译，把多个dex文件合并成一个oat文件执行。
    2、对于Android 5之前的版本Dalvik虚拟机限制每个app只能有一个dex文件classes.dex，要使用它们必须使用
        android提供的Multidex库；
        Android Multidex库的使用：
            a、需要升级Android build tools和Android support repository到21.1，这是支持Multidex的最低版本
            b、在Android gradle defaultConfig中配置multiDexEnabled为true启用multiDex，
            并同时引入multiDex库compile 'com.android.support:multidex:1.0.1'
            c、在配置文件中加上：
            <application 
                ....
                android:name="android.support.multidex.MultiDexApplication"
                ....
            </application>
            或者继承MultiDexApplication
            或者继承三方提供的Application,重写attachBaseContext(Context base)
            protected void attachBaseContext(Context base){
                super.attachBaseContext(base);
                MultiDex.install(this);
            }