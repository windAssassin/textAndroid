adb命令行：
1、adb shell pm list packages -3  //查看第三方应用的包名

2、adb push a端文件路径 b端文件路径 //从a端传输文件到b端

3、adb shell dumpsys cpuinfo/meninfo + 包名   //查看cpu使用情况或者某个应用的内存使用情况

4、adb shell pm clear + 应用报名 //清楚应用的缓存信息

5、adb logcat -v time *:W > + 日志地址 //输出warning以上级别的日志（带有时间）
	日志级别：
		D: debug
		I: info
		W: warning
		E: error
		F: fatal
		S: silent