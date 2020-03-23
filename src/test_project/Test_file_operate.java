package test_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test_file_operate {

	public List<String> handleFile() {
		File file = new File("C:\\Users\\binee\\Desktop\\test.txt");
		List<String> lines = new ArrayList<String>();
		BufferedReader br = null;
		InputStreamReader isr = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(line);

				boolean readNext = countChar(sb.toString(), '"', 0) % 2 == 1;
				// 如果双引号是奇数的时候继续读取。考虑有换行的是情况
				while (readNext) {
					line = br.readLine();
					if (line == null) {
						return null;
					}
					sb.append(line);
					readNext = countChar(sb.toString(), '"', 0) % 2 == 1;
				}
				lines.add(sb.toString());
				System.out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Read CSV file failure :" + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Close stream failure :" + e.getMessage());
			}
		}
		return lines;
	}
	
	private static int countChar(String str, char c, int start) {
		int index = str.indexOf(c, start);
		return index == -1 ? 0 : countChar(str, c, index + 1) + 1;
	}

	public static void main(String[] args) {
		Test_file_operate test = new Test_file_operate();
		test.handleFile();
	}

}
