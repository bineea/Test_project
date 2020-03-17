package test_project.leetcode;

public class Test_28_spell_word {

	public int countCharacters(String[] words, String chars) {
		int res = 0;
		
		for(int i=0; i<words.length; i++) {
			if(words[i].length() > chars.length())
				continue;
			int single = words[i].length();
			char[] charsArray = chars.toCharArray();
			char[] wordArray = words[i].toCharArray();
			for(int x=0; x<wordArray.length; x++) {
				
				for(int y=0; y<charsArray.length; y++) {
					if(wordArray[x] == charsArray[y]) {
						charsArray[y] = '#';
						single--;
						break;
					}
				}
			}
			if(single == 0) res += words[i].length();
		}
		
		return res;
    }
	
	public static void main(String[] args) {
		Test_28_spell_word test = new Test_28_spell_word();
		String[] words0 = new String[] {"cat","bt","hat","tree"};
		System.out.println(test.countCharacters(words0, "atach"));
	}
}
