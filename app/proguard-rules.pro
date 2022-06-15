# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


#proguard主要包括以下主要功能：
#一、压缩（shrink），用于监测没有使用的类、方法、属性和字段
#二、优化（optimize），对字节码进行优化，移除无用指令
#三、混淆（Obfuscate），使用一些特殊的字段对类、方法、字段进行重命名
#四、预检（preverify），主要是在java平台对处理后的代码进行预检

#proguard的配置主要包括以下几个方面
#一、基本配置，如混淆算法、压缩等级、混淆范围等
#二、需要keep不能混淆的项

#proguard混淆主要包括以下几个方面：
#一、基本配置，大部分项目都差不多
#二、基本的keep项，包括四大组件等
#三、三方引用的lib包混淆，需要查阅三方对应的混淆文件
#四、其他的keep项，包括：实体类、json解析类、webview和js相关的调用模块，以及反射相关的类和方法

#指定压缩级别
-optimizationpasses 5

#混淆私有库的类成员
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

#设置混淆算法（算法什么意思？）
-optimizations !code/simplification/arithmetic, !field/*, !class/merging/*

#把混淆类中的方法名也混淆
-useuniqueclassmembernames

#不做预检验，加快混淆速度（preverify是proguard的四大步骤之一，可以加快混淆速度）
-dontpreverify

#忽略警告
-ignorewarnings

#混淆时不使用大小写混合，只使用小写（大小写混合使用，容易造成class文件相互覆盖）
-dontusemixedcaseclassnames

#优化时允许访问有修饰符的类和类成员
-allowaccessmodification

#将文件重命名为'SourceFile'字符串
-renamesourcefileattribute SourceFile

#保留行号
-keepattributes SourceFile,LineNumberTable

#保留泛型
-keepattributes Signature

#保留注解
-keepattributes *Annotation*,InnerClasses

# Parcelable
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Serializable
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保留R下面的资源
-keep class **.R$* {*;}

# 保留四大组件，自定义的Application,Fragment等这些类不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

## support
-dontwarn android.support.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *;}
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留本地native方法不被混淆
-keepclasseswithmembers class * {
    native <methods>;
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

#保留在Activity中的方法参数是view的方法，
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For XML inflating, keep views' constructoricon.png    自定义view
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# androidx 混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
-printconfiguration
-keep,allowobfuscation @interface androidx.annotation.Keep

-keep @androidx.annotation.Keep class *
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}


#三方代码混淆 -keep class 类所在的包.** { *; }
#或者 -keep interface 接口所在的包.**{*;}
# google gson
-keep class org.json { *; }
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.** { *;}

# OkHttp3
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-keep interface com.squareup.okhttp3.** { *;}
-dontwarn okio.**
-keep class okio.**{*;}
-keep interface okio.**{*;}

# WebView
-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient
-keep public class android.webkit.WebView
-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient


#配置log过滤
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

