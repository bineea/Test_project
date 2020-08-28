package test_project.exercise.leetcode;

/**
 * ��һ�������ʼ�����ɸ�Ԫ�ذᵽ�����ĩβ�����ǳ�֮Ϊ�������ת������һ����������������һ����ת�������ת�������СԪ�ء�
 * ���磬���� [3,4,5,1,2] Ϊ [1,2,3,4,5] ��һ����ת�����������СֵΪ1�� 
 * 
 * ����˼·��
 * ��������Ĳ����������ȿ���ʹ�ö��ַ���������Խ����������Լ���ʱ�临�ӶȽ��͵���������
 * ����firstNoָ���������ֵ�±꣬����endNoָ�������Ҷ�ֵ�±꣬
 * middleNo = (firstNo + endNo) / 2 Ϊÿ�ζ��ֵ��е㣬middleȡֵ��Ҫ����ȡ������//����ʾȡֵ����ȡ����
 * 
 * 1.numbers[middleNo] > numbers[endNo]�� 
 * middleNo һ���� ���������� �У�����ת�� x һ���� [middleNo+1,endNo] �������ڣ����ִ�� firstNo=middleNo+1��
 * 2.numbers[middleNo] < numbers[endNo]��
 * middleNo һ���� ���������� �У�����ת�� x һ����[firstNo,middleNo] �������ڣ����ִ�� endNo=middleNo��
 * 3.numbers[middleNo] = numbers[endNo]��
 * �޷��ж� middleNo ���ĸ����������У����޷��ж���ת�� x �� [firstNo,middleNo]���� [middleNo+1,endNo] �����С����������ִ��endNo=endNo-1��С��Χ
 * 
 * �ص���ͣ�
 * 1.������numbers[middleNo] �� numbers[endNo]��Ƚ�
 * ����Ƚ�numbers[middleNo] �� numbers[firstNo]����numbers[middleNo] > numbers[firstNo]ʱ���޷��ж���ת��x��������
 * ���ӣ� �� firstNo=0,endNo=4,middleNo=2ʱ���� numbers[middleNo] > numbers[firstNo] ��������ʾ���ó���ͬ�����
* numbers=[1,2,3,4,5]��ת�� x=0 �� middleNo �����������飨��ʾ��ֻ�����������飩��
* numbers=[3,4,5,1,2]��ת�� x=3 �� middleNo �����������顣
* 
* 2.numbers[middleNo] = numbers[endNo]��ִ��endNo=endNo-1��С��Χ
* ��Ϊ�ȽϵĽڵ���middleNo��endNo���������޷��жϾ������������£�ֻ�ܲ���endNo�����ƶ�1��λ�ã�
* ��Ϊnumbers[middleNo] = numbers[endNo]
* ���Լ�ʹ numbers[endNo]Ϊ��Сֵ������numbers[middleNo]�Դ����뷶Χ�У����Բ��ᶪʧ��Сֵ
* 
 * @author bineea
 *
 */
public class Test_10_revolve_array {
	
	public int minArray_BL(int[] numbers) {
        int first = numbers[0];
        int res = first;
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] < first) {
                res = numbers[i];
                break;
            }
        }

        return res;
    }
	
	public int minArray_FINAL(int[] numbers) {
		int firstNo=0, endNo=numbers.length-1, middleNo=0;
		while(firstNo < endNo) {
			middleNo = (int) Math.floor((firstNo + endNo) / 2);
			if(numbers[middleNo] > numbers[endNo]) {
				firstNo = middleNo + 1;
			} else if(numbers[middleNo] < numbers[endNo]) {
				endNo = middleNo;
			} else if(numbers[middleNo] == numbers[endNo]) {
				endNo -= 1;
			}
		}
		
		return numbers[firstNo];
	}
	
	public static void main(String[] args) {
		Test_10_revolve_array test = new Test_10_revolve_array();
		int[] array = {1,2,1};
		int result = test.minArray_FINAL(array);
		System.out.println(result);
	}
}
