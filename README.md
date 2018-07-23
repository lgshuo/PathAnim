# 简介
	一个根据路径去执行动画的框架

# 效果图
![](https://github.com/lgshuo/PathAnim/blob/master/gif/anim.gif)

# 使用方法

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
    	    implementation 'com.github.lgshuo:PathAnim:v1.0.4'
    }



	PathAnimator pathAnimator = new PathAnimator();
 	pathAnimator.moveTo(x,y);		//移动到动画起始点(相对位置)
    pathAnimator.arcToarcTo(x,y,radius,angle,startAngle)	//添加圆弧路径
    pathAnimator.lineTo(x,y);		//添加直线路径
	pathAnimator.curveTo(c0x,c0y,c1x,c1y,x,y)	//添加贝赛尔曲线路径


	pathAnimator.startAnim(View, during);		执行动画