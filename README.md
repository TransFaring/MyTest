# 网易薄荷 Android SDK 集成文档  #
网易薄荷直播间SDK（aar），适用于网易新闻客户端接入，实现观看网易薄荷主播直播功能，当前更新至0.0.3.06


## 简介 ##
网易薄荷直播间SDK统一以“MintLiveSDK”类为唯一出口类，使用该类中的静态方法在BaseApplicaton（extends Application）中完成初始化，并在业务开发层执行相关方法，完成用户进入直播间，触发登陆、充值，登陆、充值回调刷新页面等一系列操作，在使用前需要配置相应权限和依赖库，以下是可供执行的方法列表（共计11个）：

格式：(返回值类型) 方法名 (参数名);

    //以下4个需在BaseApplication的onCreate()方法中完成
    //初始化全局
    (void) MintLiveSDK.init(Application context);
    //设置服务器环境（可选设置，默认为线上环境）
    (void) MintLiveSDK.setEnvironment(EnvironmentType environmentType);
    //初始化登陆
    (void) MintLiveSDK.initLogin(MintUserLoginAdapter loginAdapter);
    //初始化公共组件，分享、充值
    (void) MintLiveSDK.initCommon(MintCommonAdapter mintCommonAdapter);
    
    //以下5个需在业务开发层完成
    //进入直播间
    (void) MintLiveSDK.enterLiveRoom(Activity activity, int roomId);
    //登录成功后回调
    (void) MintLiveSDK.loginComplete(Object params);
    //退出登录后回调
    (void) MintLiveSDK.logoutComplete(Object params);
    //分享成功后回调
    (void) MintLiveSDK.shareComplete(Object params);
    //充值成功后回调
    (void) MintLiveSDK.rechargeComplete(Object params);
    
    //以下2个为提供的工具类
    //获得进入直播间的Intent
    (Intent) getEnterLiveRoomIntent(Activity activity, int roomId)
    //获得网易薄荷首页热门数据
    (void) MintLiveSDK.void getHotRoomData(int pageNo, int pageSize,IJsonObjectParse<LiveRoomList> iJsonObjectParse);

## 集成说明 ##
### 1.准备工作 ###
将SDK文件夹下的aar文件拷贝至宿主工程"根目录/{ModuleName}/libs"目录下

### 2.Module build.gradle中配置必要依赖###
以下是SdkDemo工程中所有的依赖清单，宿主工程可相应去重


    compile 'com.android.support:appcompat-v7:' + rootProject.ext.androidSupportVersion
    compile 'com.android.support:recyclerview-v7:' + rootProject.ext.androidSupportVersion
    compile 'com.android.support:cardview-v7:' + rootProject.ext.androidSupportVersion
	//sdk aar文件
    compile(name:'mint-common-platform-0.0.3.06', ext:'aar')
    compile 'com.google.code.gson:gson:2.7'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    //事件通知
    compile 'de.greenrobot:eventbus:2.4.0'
    //网络
    compile 'com.squareup.retrofit2:retrofit:2.2.0'
    compile 'com.squareup.retrofit2:converter-gson:2.2.0'
    //日志
    compile 'com.orhanobut:logger:1.3'
    //图片加载Fresco
    compile 'com.facebook.fresco:fresco:0.12.0'
    compile 'com.facebook.fresco:animated-base-support:0.12.0'
    //statusBar设置库
    compile 'com.readystatesoftware.systembartint:systembartint:1.0.4'
    // 添加依赖。注意，版本号必须一致。
    // 基础功能 (必需)
    compile 'com.netease.nimlib:basesdk:3.5.5'
    // 音视频需要
    compile 'com.netease.nimlib:avchat:3.5.5'
    // 聊天室需要
    compile 'com.netease.nimlib:chatroom:3.5.5'
    // 实时会话服务需要
    compile 'com.netease.nimlib:rts:3.5.5'
    //直播间左右切换直播间清屏的控件，该依赖包需要在project的依赖中加入库地址：maven { url "https://jitpack.io" }
    compile 'com.github.Yellow5A5:ClearScreenHelper:1.0.2'
    compile 'com.google.android.gms:play-services-appindexing:9.4.0'
    compile 'com.kevin:crop:1.0.2'
    compile 'io.reactivex:rxjava:1.2.2'
    compile 'io.reactivex:rxandroid:1.2.0'
	//loading动画
    compile 'com.tuyenmonkey:mkloader:1.2.0'
### 3.AndroidManifest.xml中配置必要权限###
以下是必须的权限
 <!-- 薄荷直播sdk需要的权限声明 -->

    <!--云信消息接收权限-->
    <!-- SDK 权限申明, 第三方 APP 接入时，请将 com.mint.sdk 替换为自己的包名 -->
    <permission
        android:name="com.mint.sdk.permission.RECEIVE_MSG"
        android:protectionLevel="signature" />
    <uses-permission android:name="com.mint.sdk.permission.RECEIVE_MSG" />

    <!-- 外置存储存取权限 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

    <!-- 访问网络状态 -->
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

    <!-- 云信消息，控制呼吸灯，振动器等，用于新消息提醒 -->
    <uses-permission android:name="android.permission.FLASHLIGHT"/>
    <uses-permission android:name="android.permission.VIBRATE"/>

    <!-- 摄像头、麦克风权限多媒体 -->
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE"/>

    <!-- 如果需要实时音视频通话模块，下面的权限也是必须的。否则，可以不加 -->
    <uses-permission android:name="android.permission.BLUETOOTH"/>
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS"/>
    <uses-permission android:name="android.permission.BROADCAST_STICKY"/>

    <!-- GPS -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>

    <!-- 允许程序使用PowerManager WakeLocks以防止处理器休眠或者屏幕锁屏 -->
    <uses-permission android:name="android.permission.WAKE_LOCK"/>

    <!--摄像头和自动对焦的硬件请求-->
    <uses-feature android:name="android.hardware.camera"/>
    <uses-feature android:name="android.hardware.camera.autofocus"/>
### 4.BaseApplicationd中onCreate()执行初始化###
提示：为避免代码冗余，也可自建工具类实现下列方法，并在onCreate()中调用

   
    @Override
    public void onCreate() {
        super.onCreate();

         //SDK全局初始化
        MintLiveSDK.init(this);

        //SDK登陆初始化，创建登陆适配器对象，重写登陆方法
        MintLiveSDK.initLogin(new MintUserLoginAdapter() {
			//登录状态
            @Override
            public boolean isLogin() {
                return false;
            }
			//token
            @Override
            public String getToken() {
                return null;
            }

            @Override
            public String getUserId() {
                return null;
            }

            @Override
            public String getURSConfigId() {
                return null;
            }
			//SDK触发登录时执行的回调
            @Override
            public void goToLogin(Activity activity, BaseCallBack baseCallBack) {
				//todo:宿主的登录方法
            }
			//用户需要绑定手机时执行的回调
            @Override
            public void goToBindPhoneNum(Activity activity, BaseCallBack baseCallBack) {
				//todo:宿主的绑定手机方法
            }
        });

		//SDK公共组件初始化，创建公共组建适配器对象，重写公共组件方法
        MintLiveSDK.initCommon(new MintCommonAdapter() {
			//用户进行分享时执行的回调
            @Override
            public void share(Activity activity, ShareBean shareBean, BaseCallBack baseCallBack) {
                //todo:宿主的分享方法
            }
			//用户进行充值时执行的回调
            @Override
            public void recharge(Activity activity, Object o, BaseCallBack baseCallBack) {
				//todo:宿主的充值方法
            }
        });

    }

### 5.业务开发层执行相应方法 ###
1.通过传入roomId，执行以下方法，可启动直播间Activity

     (void) MintLiveSDK.enterLiveRoom(Activity activity, int roomId);
2.登录成功后，执行以下方法，参数可选，可为null

    (void) MintLiveSDK.loginComplete(Object params);
3.退出登录成功后，执行以下方法，参数可选，可为null

    (void) MintLiveSDK.logoutComplete(Object params);
4.分享成功后，执行以下方法，参数可选，可为null

    (void) MintLiveSDK.shareComplete(Object params);
5.充值成功后，执行以下方法，参数可选，可为null

    (void) MintLiveSDK.rechargeComplete(Object params);

### 6.其他工具方法 ###
1.获得网易薄荷首页热门数据，可执行以下方法，pageNo为页码数，pageSize为每页个数，iJsonObjectParse为网络请求后回调
    
     MintLiveSDK.getHotRoomData(1, 10, new IJsonObjectParse<LiveRoomList>() {
            @Override
            public void parseObject(LiveRoomList liveRoomList) {
                if (liveRoomList == null || liveRoomList.getDataList() == null || liveRoomList.getDataList().size() == 0) {
                    return;
                }
				//todo:通过liveRoomList.getDataList()可获得一个List<Room>
             
            }

            @Override
            public void onError(String s, int i) {

            }
        });
    }
2.上述方法获得的List<Room>中的Room实体基本属性
    
    {
	   roomId:int //房间号
       niceRoomId:int //靓号
       yxRoomId:int //云信roomId
       ownerId:string //拥有者userId
       name:string //房间名称
       city:string //城市
       bulletin:string //公告
       liveCoverUrl:string //封面图
       onlineUserCount:int //当前在线人数
       roomStatus:int //房间状态
       createTime:long //创建时间
       roomType:int //房间类型
       liveId:long //直播ID
	}

3.获得进入直播间Activity的Intent，执行以下方法，可用于跳转
    
    (Intent) getEnterLiveRoomIntent(Activity activity, int roomId)


## SdkDomo示例 ##
[https://g.hz.netease.com/mint/android-sdk-demo/tree/simple/MintSdkSimpleDemo](https://g.hz.netease.com/mint/android-sdk-demo/tree/simple/MintSdkSimpleDemo "Demo示例请点击")

