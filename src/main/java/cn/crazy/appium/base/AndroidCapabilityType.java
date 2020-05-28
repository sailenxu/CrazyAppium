package cn.crazy.appium.base;

import io.appium.java_client.remote.MobileCapabilityType;

public interface AndroidCapabilityType extends MobileCapabilityType {
	String NO_SIGN="noSign";
	String UNICODE_KEY_BOARD="unicodeKeyboard";
	String RESET_KEY_BOARD="resetKeyboard";
	String AUTO_LAUNCH="autoLaunch";
	String DONT_STOP_APP_ON_RESET="dontStopAppOnReset";
}
