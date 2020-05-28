package cn.crazy.appium.server;

import java.util.ArrayList;
import java.util.List;

import cn.crazy.appium.util.DosCmd;
import cn.crazy.appium.util.Log;



/**
 * @author 狂沙 qq289303905
 *
 */
/**
 * 此类封装appium server所有需要的端口分配的诸多方法
 *
 */
public class Port {
	private DosCmd execDos;
	private Log logger=Log.getLogger(Port.class);
	public Port(DosCmd execDos){
		this.execDos=execDos;
	}
	/**
	 * 验证端口是否被占用
	 * @param portNum
	 * @return boolean
	 */
	public  Boolean isPortUsed(int portNum) {
		List<String> portRes = new ArrayList<String>();
		boolean flag=true;
		try {
			portRes = execDos.execCmdConsole("netstat -aon|findstr " + portNum);
			if (portRes.size() > 0) {
				logger.debug(String.valueOf(portNum)+" port has been occupied");
			}else{
				logger.debug(String.valueOf(portNum)+" port unoccupied");
				flag=false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(String.valueOf(portNum)+"get port occupied status failure"+e);
		}
		return flag;
	}
	/**
	 * 基于当前连接的设备数量生成可用端口
	 * @param portStart,Starting ports
	 * @param deviceTotal,Total number of devices
	 * @return List<Integer>
	 */
	public  List<Integer> GeneratPortList(int portStart, int deviceTotal) {
		List<Integer> portList = new ArrayList<Integer>();
		while (portList.size() != deviceTotal) {
			if (portStart >= 0 && portStart <= 65535) {
				if (!isPortUsed(portStart)) {
					portList.add(portStart);
				}
				portStart++;
			}
		}
		return portList;
	}
	public static void main(String[] args) throws Exception {
//		Port p=new Port(new DosCmd());
//		Servers server=new Servers(p, new DosCmd());
//		int count=server.getDevices().size();
//		List<Integer> portList=p.GeneratPortList(4491, count);
//		for(Integer i:portList){
//			System.out.println(i);
//		}
//		List<Integer> portList2=p.GeneratPortList(2251, count);
//		for(Integer i:portList2){
//			System.out.println(i);
//		}
		
//		DosCmd cmd=new DosCmd();
//		int i=10;
//		while(cmd.execCmdConsole("netstat -ano|findstr 4723").size()==0&&i>0){
//			Runtime.getRuntime().exec("cmd /c appium");
//			Thread.sleep(10000);
//			System.out.println(i--);
//		}
		Runtime.getRuntime().exec("cmd /c appium >D://log.txt");
		Thread.sleep(10000);
		//System.out.println(p.isPortUsed(4493));
	}
}
