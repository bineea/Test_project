package test_project.basic.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.alibaba.fastjson.JSON;

/**
 * 必须继承 java.rmi.server.UnicastRemoteObject 类
 * （UnicastRemoteObject：用于导出带 JRMP 的远程对象和获得与该远程对象通信的 stub）
 * @author bineea
 *
 */
public class EvilRemoteServiceImpl extends UnicastRemoteObject implements EvilRemoteService {

	private static final long serialVersionUID = 1112528967800140413L;

	protected EvilRemoteServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String doEvil(Object... objects) throws RemoteException {
		System.out.println("(x_x)-----start-----(x_x)");
		for (Object obj : objects) {
			if (obj != null) {
				System.out.println(obj.toString());
				JSON.toJSONString(obj);
			}
		}
		System.out.println("(x_x)-----end-----(x_x)");
		return "whosyourdaddy";
	}

}
