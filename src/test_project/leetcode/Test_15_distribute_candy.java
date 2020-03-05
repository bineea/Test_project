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
 * 2.����ͨ���Ȳ�ֱ�Ӽ���ÿ���˵����ս����ʵ��ֻ����һ�Σ���ϸ���㣬��ᷢ�ֺᡢ�����ǵȲ�����
 * 
 * ���磺����60,  4
 * ��1�θ����ǹ�������[1,2,3,4]
 * ��2�θ����ǹ�������[5,6,7,8]
 * ��3�θ����ǹ�������[9,10]
 * 
 * ÿ�θ����ǹ������ᡢ�����ǵȲ����У���ȫ�ǵȲ����е���ͣ�����
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
	
	public int[] distributeCandies_TRY(int candies, int num_people) {
		int max_people = (int)Math.floor((Math.sqrt((double)1 + (double)4 * (double)1 * (double)candies * (double)2) - 1 ) * 0.5);
		int surplus_candy = candies - max_people * (max_people + 1)  / 2;
		int multiple = (int)Math.floor((double)max_people / (double)num_people);
		int[] all_people = new int[num_people];
		for(int i=0; i<num_people && i<max_people; i++) {
			if(multiple == 0 || multiple == 1) {
				all_people[i] = (int)(1 + i);
			} else {
				all_people[i] = (int)((1 + (i + 1 - 1)) * multiple + multiple * (multiple - 1) * num_people * 0.5);
			}
			
			if(num_people <= max_people && i < (max_people - num_people * multiple)) {
				all_people[i] += 1 + (i + 1 + multiple * num_people - 1) * 1;
			}
		}
		if(num_people > max_people) {
			all_people[max_people] += surplus_candy;
		} else {
			all_people[max_people - num_people * multiple] += surplus_candy;
		}
		return all_people;
	}
	
	public static void main(String[] args) {
		Test_15_distribute_candy test = new Test_15_distribute_candy();
		//System.out.println((int)Math.floor(Math.sqrt(8.0)));
		int[] ans1 = test.distributeCandies_TRY(7,4);
		for(int i=0; i<ans1.length; i++) {
			System.out.println(ans1[i]);
		}
	}
}
