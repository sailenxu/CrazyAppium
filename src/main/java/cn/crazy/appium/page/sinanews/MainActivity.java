package cn.crazy.appium.page.sinanews;

import io.appium.java_client.MobileCommand;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

public class MainActivity {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Q5S5T19529000632");
        cap.setCapability(MobileCapabilityType.APP_PACKAGE, "com.cleanmaster.mguard_cn");
        cap.setCapability(MobileCapabilityType.NO_RESET, "true");
        cap.setCapability(MobileCapabilityType.APP_ACTIVITY,"com.keniu.security.main.MainActivity");
        cap.setCapability(MobileCapabilityType.DONT_STOP_APP_ON_RESET, "true");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
//        List<WebElement> ll = driver.findElements(By.xpath("//android.widget.ListView//android.widget.RelativeLayout[@index='3']"));
//        System.out.println(ll.get(1).getText());
//        ll.get(1).click();
        driver.context("WEBVIEW");
        //跳转进新闻详情页webview
        Set context = driver.getContextHandles();
        for (Object c:context){
            if (String.valueOf(c).contains("webview"))
            System.out.println(c);
        }
        driver.quit();
    }
}
