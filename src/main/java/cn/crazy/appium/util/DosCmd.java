package cn.crazy.appium.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.crazy.appium.server.Port;

/**
 * @author 狂沙 qq289303905
 *
 */
/**
 * 此类完成windows下dos命令的执行封装
 *
 */
public class DosCmd {
	private Log logger=Log.getLogger(DosCmd.class);
	
	/**
	 * execute dos command
	 * @param dos command,String
	 * @return boolean.succeed is true,Failure is false
	 * 
	 */
	public boolean execCmd(String cmdString){
		Runtime p = Runtime.getRuntime();
		try {
				//Runtime.getRuntime().exec("cmd /c appium");
			Process process=p.exec("cmd /c "+cmdString);
			
			Thread.sleep(10000);
			//process.waitFor();
			//process.destroy();
			System.out.println("dos命令执行完成");
			logger.debug("execute dos command "+cmdString+" Succeed");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("execute dos command "+cmdString+" Failure", e);
			return false;
		}
	}
	/**
	 * get result of command, after execute dos command 
	 * @param dos command,String
	 * @return List<String>
	 */
	public List<String> execCmdConsole(String cmdString) throws InterruptedException {
		List<String> dosRes = new ArrayList<String>();
		try {
			Process process = Runtime.getRuntime().exec("cmd /c " + cmdString);
			InputStream in = process.getInputStream();
			BufferedReader inr = new BufferedReader(new InputStreamReader(in,
					"GBK"));
			String line = null;
			while ((line = inr.readLine()) != null) {
				dosRes.add(line);
			}
			process.waitFor();
			process.destroy();
			logger.debug("get result of command after execute dos command "+cmdString+" Succeed ");
		} catch (IOException e) {
			logger.error("get result of command after execute dos command "+cmdString+" Failure", e);
		}
		return dosRes;
	}
	/**
	 * kill server by pid of server
	 * @param pid
	 * @return boolean
	 */
	public boolean killServer(int pid){
		if(execCmd("taskkill -F -PID "+String.valueOf(pid))){
			logger.debug("kill server "+String.valueOf(pid)+" Succeed");
			return true;
		}else{
			logger.error("kill server "+String.valueOf(pid)+" Failure");
			return false;
		}
	}
     /**
	 * kill server by name of server,the method can kill the same server name
	 * @param the server's name
	 * @return boolean
	 */
	public boolean killServer(String serverName){
		if(execCmd("taskkill -F -im "+serverName)){
			logger.debug("kill server "+serverName+" Succeed");
			return true;
		}else{
			logger.error("kill server "+serverName+" Failure");
			return false;
		}
	}
	public static void main(String[] args) throws Exception {
		DosCmd dc=new DosCmd();
		dc.execCmd("adb devices");
		//dc.execCmd("appium -p 4490 -bp 2256 -U 192.168.56.101:5555 -g xxx.log");
//		String input="com.android.inputmethod.latin/.LatinIME";
		List<String> deviceList=new ArrayList<String>();
		deviceList=dc.execCmdConsole("adb devices");
		System.out.println(deviceList.size());
		for(String s:deviceList){
			System.out.println(s);
		}
//		List<String> udidList=new ArrayList<String>();
//		if(deviceList.size()>2){
//			for(int i=1;i<deviceList.size()-1;i++){
//				System.out.println(deviceList.get(i));
//				String[] deviceSplit=deviceList.get(i).split("\t");
//				if(deviceSplit[1].equals("device")){
//					udidList.add(deviceSplit[0]);
//				}else{
//					System.out.println("设备状态不对");
//				}
//			}
//		}else{
//			System.out.println("当前没有设备");
//		}
//		Port p=new Port(new DosCmd()); 
//		List<Integer> portList=p.GeneratPortList(4490,udidList.size());
//		for(int i:portList){
//			System.out.println(i);
//		}

		//System.out.println(input);
		//DosCmd dc=new DosCmd();
		//dc.killServer("node.exe");
		//需要生成两组可用端口
		//每组端口数量是根据当前设备数量进行生成
		//生成之后进行服务端启动命令的拼接appium -p 4492 -bp 2251 -U 192.168.56.101:5555
//		dc.execCmd("appium -p 4492 -bp 2251 -U 192.168.56.101:5555>C:\\Users\\lixionggang\\Desktop\\log.txt");
//		List<String> strList=dc.execCmdConsole("netstat -ano|findstr 4490");
////		List<String> strList=dc.execCmdConsole("adb  shell \"settings get secure default_input_method\"");
//		System.out.println(strList.size());
//		for(String s:strList){
//			System.out.println(s);
//		}
		
	}
}
