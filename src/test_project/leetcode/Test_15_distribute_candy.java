package test_project.leetcode;

/**
 * ��������һЩ�ǹ� candies����������Ƿָ��źöӵ� n = num_people ��С���ѡ�
 * ����һ��С���� 1 ���ǹ����ڶ���С���� 2 �ţ��������ƣ�ֱ�������һ��С���� n ���ǹ���
 * Ȼ�������ٻص��������㣬����һ��С���� n + 1 ���ǹ����ڶ���С���� n + 2 �ţ��������ƣ�ֱ�������һ��С���� 2 * n ���ǹ���
 * �ظ��������̣�ÿ�ζ�����һ�ζ����һ���ǹ�������������յ���ٴδӶ�����㿪ʼ����ֱ�����Ƿ������е��ǹ���ע�⣬�����������е�ʣ���ǹ�������������ǰһ�η������ǹ��ࣩ����Щ�ǹ�Ҳ��ȫ��������ǰ��С���ѡ�
 * ����һ������Ϊ num_people��Ԫ��֮��Ϊ candies �����飬�Ա�ʾ�ǹ������շַ�������� ans[i] ��ʾ�� i ��С���ѷֵ����ǹ�������
 * 
 * ����˼·��
 * �Ȳ����й�ʽ��
 * Sn=a1*n+[n*(n-1)*d]/2��Sn=[n*(a1+an)]/2
 * an=a1+(n-1)*d
 * 
 * 1.���ϱ��������ˣ�ֱ���Ǳ����ꣻ
 * 2.����ͨ���Ȳ�ֱ�Ӽ���ÿ���˵����ս����ʵ��ֻ����һ�Σ�����
 * 
 * @author bineea
 *
 */
public class Test_15_distribute_candy {

	public int[] distributeCandies_BL(int candies, int num_people) {
		int[] all_people = new int[num_people];
		
		int n = 1;
		while(candies > 0) {
			for(int i=0; i<num_people; i++) {
				int current_candies = 1 + (n - 1) * 1;
				if(candies < current_candies) {
					current_candies = candies;
				}
				all_people[i] += current_candies;
				candies -= all_people[i];
				n += 1;
			}
		}
		
		return all_people;
    }
	
	public int[] distributeCandies_FINAL(int candies, int num_people) {
		
		return null;
	}
	
	public static void main(String[] args) {
		Test_15_distribute_candy test = new Test_15_distribute_candy();
		System.out.println((int)Math.floor(Math.sqrt(8.0)));
		int[] ans1 = test.distributeCandies_BL(7, 4);
		for(int i=0; i<ans1.length; i++) {
			System.out.println(ans1[i]);
		}
	}
}
