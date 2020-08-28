package test_project.exercise.leetcode;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。 
 * 
 * 解题思路：
 * 排序数组的查找问题优先考虑使用二分法解决，可以将遍历的线性级别时间复杂度降低到对数级别
 * 设置firstNo指向数组左端值下标，设置endNo指向数组右端值下标，
 * middleNo = (firstNo + endNo) / 2 为每次二分的中点，middle取值需要向下取整（“//”表示取值向下取整）
 * 
 * 1.numbers[middleNo] > numbers[endNo]； 
 * middleNo 一定在 左排序数组 中，即旋转点 x 一定在 [middleNo+1,endNo] 闭区间内，因此执行 firstNo=middleNo+1；
 * 2.numbers[middleNo] < numbers[endNo]；
 * middleNo 一定在 右排序数组 中，即旋转点 x 一定在[firstNo,middleNo] 闭区间内，因此执行 endNo=middleNo；
 * 3.numbers[middleNo] = numbers[endNo]；
 * 无法判断 middleNo 在哪个排序数组中，即无法判断旋转点 x 在 [firstNo,middleNo]还是 [middleNo+1,endNo] 区间中。解决方案：执行endNo=endNo-1缩小范围
 * 
 * 重点解释：
 * 1.必须是numbers[middleNo] 与 numbers[endNo]相比较
 * 如果比较numbers[middleNo] 与 numbers[firstNo]，当numbers[middleNo] > numbers[firstNo]时，无法判断旋转点x所在区间
 * 例子： 当 firstNo=0,endNo=4,middleNo=2时，有 numbers[middleNo] > numbers[firstNo] ，以下两示例得出不同结果。
* numbers=[1,2,3,4,5]旋转点 x=0 ： middleNo 在右排序数组（此示例只有右排序数组）；
* numbers=[3,4,5,1,2]旋转点 x=3 ： middleNo 在左排序数组。
* 
* 2.numbers[middleNo] = numbers[endNo]；执行endNo=endNo-1缩小范围
* 因为比较的节点是middleNo和endNo，所以在无法判断具体区间的情况下，只能操作endNo向左移动1个位置，
* 因为numbers[middleNo] = numbers[endNo]
* 所以即使 numbers[endNo]为最小值，但是numbers[middleNo]仍存在与范围中，所以不会丢失最小值
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
