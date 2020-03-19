package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * ����һ��������д��ĸ��Сд��ĸ���ַ������ҵ�ͨ����Щ��ĸ����ɵ���Ļ��Ĵ���
 * �ڹ�������У���ע�����ִ�Сд������ "Aa" ���ܵ���һ�������ַ�����
 * 
 * ʵ����
 * ����:
 * "abccccdd"
 * ���:
 * 7
 * ����:
 * ���ǿ��Թ������Ļ��Ĵ���"dccaccd", ���ĳ����� 7��
 * 
 * ����˼·��
 * ����ÿ���ַ� ch�������������� v �Σ����ǿ���ʹ�ø��ַ� v / 2 * 2 �Σ��˴���5 / 2 * 2 == 4�����ڻ��Ĵ��������Ҳ�ֱ���� v / 2 ���ַ� ch������ / Ϊ���������������� "a" ������ 5 �Σ���ô���ǿ���ʹ�� "a" �Ĵ���Ϊ 4�����Ĵ�����������ֱ���� 2 �� "a"��
 * ������κ�һ���ַ� ch �ĳ��ִ��� v Ϊ�������� v % 2 == 1������ô���Խ�����ַ���Ϊ�������ģ�ע��ֻ�������һ���ַ���Ϊ�������ġ�
 * �ڴ����У������� ans �洢���Ĵ��ĳ��ȣ������ڱ����ַ�ʱ��ans ÿ�λ����� v / 2 * 2����� ans һֱΪż����
 * ���ڷ����˵�һ�����ִ���Ϊ�������ַ������ǽ� ans ���� 1������ ans ��Ϊ�������ں��淢���������������ε��ַ�ʱ�����ǾͲ��ı� ans ��ֵ�ˡ�
 * 
 * @author bineea
 *
 */
public class Test_30_long_palindrome {
	
	public int longestPalindrome_BL(String s) {
		int res = 0;
		
		char[] array = s.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0; i<array.length; i++) {
			if(map.get(array[i]) == null) {
				map.put(array[i], 1);
			} else {
				map.put(array[i], map.get(array[i])+1);
			}
		}
		boolean existOdd = false;
		for(Character c : map.keySet()) {
			if(map.get(c) % 2 == 0) {
				res += map.get(c);
			} else if(map.get(c) % 2 != 0 && map.get(c) >= 3) {
				res += map.get(c) - 1;
				existOdd = true;
			} else if(map.get(c) % 2 != 0 && map.get(c) < 3) {
				existOdd = true;
			}
		}
		
		return existOdd ? res + 1 : res;
    }
	
	public int longestPalindrome_FINAL(String s) {
		int res = 0;
		
		int[] count = new int[128];
		for(char c : s.toCharArray()) {
			count[c]++;
		}
		for(int i=0; i<count.length; i++) {
			res += count[i] / 2 * 2;
			if(count[i] % 2 != 0 && res % 2 == 0) {
				res++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Test_30_long_palindrome test = new Test_30_long_palindrome();
		System.out.println(test.longestPalindrome_FINAL("abccccdd"));
		System.out.println(test.longestPalindrome_FINAL("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
	}
}
