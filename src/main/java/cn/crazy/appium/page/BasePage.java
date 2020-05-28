package cn.crazy.appium.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import cn.crazy.appium.base.AndroidDriverBase;
import cn.crazy.appium.util.ProUtil;

public class BasePage {
	public String curActivity;
	public String pageSource;
	public AndroidDriverBase driver;
	public BasePage(AndroidDriverBase driver){
		this.driver=driver;
	}
	//获取当前activity
	public String getCurActivity(){
		return driver.currentActivity();
	}
	public String getPageSource(){
		return driver.getPageSouce();
	}
	//输入
	public void sendkeys(AndroidElement element,String value){
		if(element!=null){
			element.sendKeys(value);
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//直接定位并输入
	public void sendkeys(By by,String value){
		AndroidElement element=driver.findElement(by);
		sendkeys(element,value);
	}
	//点击
	public void click(AndroidElement element){
		if(element!=null){
			element.click();
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//定位并点击
	public void click(By by){
		AndroidElement element=driver.findElement(by);
		click(element);
	}
	//清除
	public void clear(AndroidElement element){
		if(element!=null){
			element.clear();
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//逐个清除，对于密码输入框是无效的
	public void clearOneByOne(AndroidElement element){
		if(element!=null){
			element.click();
			driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);
			String text=element.getText();
			for(int i=0;i<text.length();i++){
				driver.pressBackspace();
			}
		}else{
			System.out.println("元素没有定位到，是null");
		}
	}
	//输入内容直到正确
	public void sendkeysUntilCorrect(AndroidElement element,String str){
		if(element!=null){
			boolean flag=true;
			element.sendKeys(str);
			while(flag){
				if(str.equals(element.getText())){
					flag=false;
				}else{
					element.sendKeys(str);
				}
			}
		}else{
			System.out.println("元素为null");
		}
	}
	//坐标元素点击，针对一些能定位到整体元素但具体元素无法定位并且具有规律性的元素点击
	//思路：获取整体元素，将整体元素分为多行多列元素，取每一个子元素的中心坐标进行点击
	public List<int[]> getElementByCoordinates(AndroidElement element,int rows,int columns){
		int[] coordinate=new int[2];
		List<int[]> elementResolve=new ArrayList<int[]>();
		if(element!=null){
			int startx=element.getLocation().getX();//起始点坐标x
			int starty=element.getLocation().getY();//起始点坐标y
			int offsetx=element.getSize().getWidth();//该元素的宽
			int offsety=element.getSize().getHeight();//该元素的高
			for(int i=0;i<rows;i++){
				for(int j=0;j<columns;j++){
					coordinate[0]=startx+(offsetx/2*columns)*(2*j+1);
					coordinate[1]=starty+(offsety/(2*rows)*(2*i+1));
					elementResolve.add(coordinate);
				}
			}
		}
		return elementResolve;
	}
	//根据整体元素拆分后的规律子元素索引点击元素
	public void clickElementByCoordinate(AndroidElement element,int rows,int columns,int index){
		if(element!=null){
			List<int[]> elementResolve=getElementByCoordinates(element,rows,columns);
			if(!elementResolve.isEmpty()&&elementResolve!=null){
				driver.clickByCoordinate(elementResolve.get(index)[0],elementResolve.get(index)[1]);
			}else{
				System.out.println("坐标集合为空");
			}
		}else{
			System.out.println("元素为null");
		}
	}
	//九宫格手势解锁,参数indexs是密码数字组成的数组，参数indexs={1,2,5,6,9,8}
	public void wakeByGestures(AndroidElement element,int[] indexs){
		if(element!=null){
			List<int[]> elementResolve=getElementByCoordinates(element,3,3);
			TouchAction ta=null;
			if(indexs.length>0){
				ta=new TouchAction(driver).press(elementResolve.get(indexs[0]-1)[0], elementResolve.get(indexs[0]-1)[1]).waitAction(500);
			}
			for(int i=1;i<indexs.length;i++){
				ta.moveTo(elementResolve.get(indexs[i])[0]-elementResolve.get(indexs[i-1])[0], elementResolve.get(indexs[i])[1]-elementResolve.get(indexs[i-1])[1]).waitAction(500);
			}
			ta.release().perform();
		}
	}
}
