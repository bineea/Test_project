package test_project.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 
 * 实例：
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 
 * 解题思路：
 * 对于每个字符 ch，假设它出现了 v 次，我们可以使用该字符 v / 2 * 2 次（此处：5 / 2 * 2 == 4），在回文串的左侧和右侧分别放置 v / 2 个字符 ch，其中 / 为整数除法。例如若 "a" 出现了 5 次，那么我们可以使用 "a" 的次数为 4，回文串的左右两侧分别放置 2 个 "a"。
 * 如果有任何一个字符 ch 的出现次数 v 为奇数（即 v % 2 == 1），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。
 * 在代码中，我们用 ans 存储回文串的长度，由于在遍历字符时，ans 每次会增加 v / 2 * 2，因此 ans 一直为偶数。
 * 但在发现了第一个出现次数为奇数的字符后，我们将 ans 增加 1，这样 ans 变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变 ans 的值了。
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
