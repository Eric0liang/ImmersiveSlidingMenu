#完美沉浸式侧滑
###效果预览
![gif](https://github.com/Eric0liang/ImmersiveSlidingMenu/blob/master/sliding.gif)

###源码下载地址:[https://github.com/Eric0liang/ImmersiveSlidingMenu](https://github.com/Eric0liang/ImmersiveSlidingMenu)
###apk下载地址:[下载最新版本](http://pan.baidu.com/s/1milTSPM)

##前言
ImmersiveSlidingMenu是在我实际项目中用到的侧滑菜单实现沉浸式，现在开源共享到gitHub上学习交流。
首先要说明的是沉浸式只适合android**4.4或以上**的系统，也就是API>19(**KITKAT**)才能看到状态栏沉浸式效果。

##技术详述
* 侧滑功能<br/>
侧滑功能依赖第三方开源项目[SlidingMenu](https://github.com/jfeinstein10/SlidingMenu),找到类*`SlidingMenu`* 315行修改如下： 

    ```
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      mActionbarOverlay = true;
    } else {
      mActionbarOverlay = false;
    }
    ```
 
* 实现沉浸式<br/>
MainActivity初始化左侧菜单时把flag设置成 FLAG_TRANSLUCENT_STATUS，支持透明状态栏
 
    ```
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = this.getWindow();
      WindowManager.LayoutParams layoutParams = window.getAttributes();
      layoutParams.flags |= WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
      window.setAttributes(layoutParams);
    }
    ```
 
  然后获取状态栏高度实现主菜单的沉浸式
  
  ```
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //获取状态栏高度
    int statusbarHeight = getStatusBarHeight();
    LinearLayout top_head = (LinearLayout) this.findViewById(R.id.top_head);
    top_head.setPadding(0, statusbarHeight, 0, 0);
  }
  ```
  
  同理左侧菜单实现沉浸式如下：
  
  ```
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    int statusbarHeight = getStatusBarHeight();
    LinearLayout top_head = (LinearLayout) view.findViewById(R.id.main_left_fragment);
    top_head.setPadding(0, statusbarHeight, 0, 0);
  }
  ```

##开发过程中遇到的问题
```
line.getBackground().mutate().setAlpha(255 * 3 / 10);
//line.getBackground().setAlpha(255 * 3 / 10);
```

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用一下联系方式跟我交流

* 邮件(liangjiangli@bluemoon.com.cn)
* QQ: 839643398




    
   
