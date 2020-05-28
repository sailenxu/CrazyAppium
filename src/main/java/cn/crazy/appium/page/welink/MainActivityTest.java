package cn.crazy.appium.page.welink;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cn.crazy.appium.network.study.DriverInit;
public class MainActivityTest {
	AndroidDriver<AndroidElement> driver;
	@Parameters({"udid","port"})
	@BeforeClass
	public void initActivity(String udid,String port) throws Exception{
		DriverInit init=new DriverInit();
		driver=init.driverInit(false,udid, port);
	}
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
	@Test
	public void test() throws Exception{
		MainActivity.randomClickInChange(driver, 20);
	}
}
