package cn.crazy.appium.page;

import org.openqa.selenium.By;

import cn.crazy.appium.base.AndroidDriverBase;
import cn.crazy.appium.server.Port;
import cn.crazy.appium.util.GetByLocator;
import cn.crazy.appium.util.Log;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class LoginPage extends BasePage{
	private Log logger=Log.getLogger(LoginPage.class);
	private AndroidElement loginOrReg;
	private AndroidElement userName;
	private AndroidElement pwd;
	private AndroidElement loginBtn;
	private AndroidElement reg;
	private AndroidElement name;
	private AndroidElement next;
	private AndroidElement regBtn;
	
	
	public LoginPage(AndroidDriverBase driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	//登录
//	public HomePage login(String username,String password){
//		loginOrReg=driver.findElement(GetByLocator.getLocator("loginOrReg"));
//		if(loginOrReg!=null){
//			loginOrReg.click();
//			System.out.println("不是null");
//		}
//		userName=driver.findElement(GetByLocator.getLocator("username"));
//		if(userName!=null){
//			userName.sendKeys(username);
//		}
//		
//		pwd=driver.findElement(GetByLocator.getLocator("password"));
//		if(pwd!=null){
//			pwd.sendKeys(password);
//		}
//		
//		loginBtn=driver.findElement(GetByLocator.getLocator("loginbtn"));
//		if(loginBtn!=null){
//			loginBtn.click();
//		}
//		
//		return new HomePage(driver);
//	}
	public HomePage login(String username,String pwd){
		super.click(GetByLocator.getLocator("loginOrReg"));
		logger.info("点击成功");
		super.sendkeys(GetByLocator.getLocator("username"), username);
		super.sendkeys(GetByLocator.getLocator("password"), pwd);
		
		super.click(GetByLocator.getLocator("loginbtn"));
		logger.info("点击登录成功");
		return new HomePage(driver); 
	}
	public void regTest(String username,String pwd,String name){
		super.click(GetByLocator.getLocator("loginOrReg"));
		super.sendkeys(GetByLocator.getLocator("username"),username);
		super.sendkeys(GetByLocator.getLocator("password"), pwd);
		super.click(GetByLocator.getLocator("reg"));
		super.sendkeys(GetByLocator.getLocator("name"), name);
		super.click(GetByLocator.getLocator("regbtn"));
	}
	
}
