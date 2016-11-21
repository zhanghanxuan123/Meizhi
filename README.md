# Meizhi
妹纸图

## APP设计

### UI
首页瀑布流模型，点击item实现类似微信微博查看大图，长按图片(因为我的Dialog里按钮点击不起T_T)实现保存到手机sd卡

### 代码
未用MVP模式开发，用Retrofit+RxJava 获取网络数据，Glide加载网络图片 ，RecyclerView实现瀑布流。加载大图的界面,创建一个全屏的Dialog，
ViewPager实现左右滑动查看

#### 依赖库
* [RxJava](https://github.com/ReactiveX/RxJava) 
* [OkHttp](https://github.com/square/okhttp)
* [Glide](https://github.com/bumptech/glide)
* [Retrofit](https://github.com/square/retrofit)
