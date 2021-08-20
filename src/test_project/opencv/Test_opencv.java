package test_project.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * 需要注意：需要将opencv安装目录\build\java\x64下的dll文件拷贝复制到jdk安装目录\bin目录下，解决引入library失败的问题
 * @author binee
 *
 */
public class Test_opencv {
	
	public static void main(String[] args) {
		System.out.println("Welcome to OpenCV "+Core.VERSION);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
		System.out.println("mat = "+mat.dump());
	}

}
