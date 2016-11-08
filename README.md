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
![](https://github.com/Eric0liang/ImmersiveSlidingMenu/blob/master/shot.png)

截图中设置不透明度的白色线，实现代码如下：
```
View line = view.findViewById(R.id.view_line);
line.getBackground().mutate().setAlpha(255 * 3 / 10);
//line.getBackground().setAlpha(255 * 3 / 10);
```
没有调用*mutate()*引发的问题如下：<br/>
line.getBackground()会得到ColorDrawable对象设置3/10的不透明度，然后有些手机的控件背景颜色与line一样的话，就会出现3/10的不透明度。
比如line的背景属性是*android:background="#fff"*，也就是说LinearLayout等其他布局或控件用到*#fff*时都会出现不透明度，而不是白色！
####为什么会出现这种情况？
原因是每个 Drawable 类对象类都关联有一个 ConstantState 类对象，这是为了保存 Drawable 类对象的一些恒定不变的数据，如果从同一个 res 中创建的 Drawable 类对象，为了节约内存，它们会*共享同一个 ConstantState 类对象*。比如一个 ColorDrawable 类对象，它会关联一个 ColorState 类对象，color 的颜色值是保存在 ColorState 类对象中的。如果修改 ColorDrawable 的颜色值，会修改到 ColorState 的值，会导致和 ColorState 关联的所有的 ColorDrawable 的颜色都改变。在修改 ColorDrawable 的属性的时候，需要先调用 public Drawable mutate() 方法，让 Drawable 复制一个新的 ConstantState 对象关联。

##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(liangjiangli@bluemoon.com.cn)
* QQ: 839643398




    
   
