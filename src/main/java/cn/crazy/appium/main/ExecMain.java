package cn.crazy.appium.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import cn.crazy.appium.server.Port;
import cn.crazy.appium.server.Servers;
import cn.crazy.appium.util.DosCmd;
import cn.crazy.appium.util.FileUtil;
import cn.crazy.appium.util.XmlUtil;

public class ExecMain {
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";  
	private static String path=System.getProperty("user.dir");
//	public void init(){
//		Servers servers=new Servers(new Port(new DosCmd()), new DosCmd());
//		DosCmd dc=new DosCmd();
//		if(dc.killServer("node.exe")){
//			try {
//				servers.startServers();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				XmlUtil.createTestngXml("cn.crazy.appium.testcases.ZhihuLoginTest");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			System.out.println("清除appium服务失败");
//		}
//	}
//	public  void startBat(String filePath) throws IOException {
//		Runtime p = Runtime.getRuntime();
//		p.exec("cmd /c start "+filePath);
//	}
//	public void run(){
//		creatBatPackage();
//		creatRunBat();
//		try {
//			init();
//			startBat(path+"\\build.bat");
//			//startBat(path+"\\run.bat");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public  void creatBatPackage() {
//		try {
//			FileUtil.writeCoverageFile("cd /d "+path+" \r\n"+"mvn test",path+"\\build.bat");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	public  void creatRunBat() {
//		try {
//			FileUtil.writeCoverageFile("cd /d "+path+"\\target \r\n"+"java -jar CrazyAppium-0.0.1-SNAPSHOT.jar",path+"\\run.bat");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	public static void main(String[] args) throws Exception {
		System.setProperty(ESCAPE_PROPERTY, "false"); 
		AppiumInit.init();
        List<String> suites = new ArrayList<String>();
        suites.add(System.getProperty("user.dir")+"\\testng.xml");
        TestNG tng = new TestNG();
        tng.setTestSuites(suites);
        tng.run();
//		   String encoding = System.getProperty("file.encoding");
//	        System.out.println("Default System Encoding: " + encoding);
		//tt.runSuitesLocally();
	}
}
