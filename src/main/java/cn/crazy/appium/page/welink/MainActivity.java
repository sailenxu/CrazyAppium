package cn.crazy.appium.page.welink;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.By;

public class MainActivity {
	// 在下方切换条随机点击
	public static void randomClickInChange(AndroidDriver<AndroidElement> driver, int num) throws Exception {
		AndroidElement change = null;
		try {
			change = driver.findElement(By
					.xpath("//android.widget.LinearLayout[@index='2']"));
			if (change != null) {
				int x = change.getLocation().getX();
				int y = change.getLocation().getY();
				for (int i = 0; i < num; i++) {
					int xRandom = x
							+ (int) (Math.random() * (change.getSize()
									.getWidth()));
					int yRandom = y
							+ (int) (Math.random() * (change.getSize()
									.getHeight()));
					driver.tap(1, xRandom, yRandom, 300);
				}
			} else {
				driver.quit();
			}
		} catch (Exception e) {
			System.out.println("change时偏离服务了！");
			StopApp();
			Thread.sleep(5000);
			StartApp();
			Thread.sleep(10000);
			// driver.quit();
			// System.exit(0);
		}
	}

	public static void StopApp() throws Exception {
		String cmd = "adb shell am force-stop com.wedrive.android.welink";
		Runtime.getRuntime().exec(cmd);
	}

	public static void StartApp() throws Exception {
		String cmd = "adb shell am start com.wedrive.android.welink/.MainActivity";
		Runtime.getRuntime().exec(cmd);
	}
}
