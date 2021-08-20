package test_project.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 * ��Ҫע�⣺��Ҫ��opencv��װĿ¼\build\java\x64�µ�dll�ļ��������Ƶ�jdk��װĿ¼\binĿ¼�£��������libraryʧ�ܵ�����
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
