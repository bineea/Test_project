package test_project;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * ѩ���㷨-���ɷֲ�ʽID
 * @author bineea
 *
 */
public class Test_snowflake {

	//��ʼʱ��
	private static final String startTime = "2015-06-01 00:00:00.000";
	private static final long startTimeMills;
	
	//��������id���ֵ
	private static final long MAX_WORK_NUM;
	//���к����ֵ
	private static final long MAX_ORDER_NUM;
	
	//��һ��id��ʱ�����Ӧֵ
	private static volatile long lastTimeVal = -1L;
	//��һ��id�����кŶ�Ӧֵ
	private static volatile long lastOrderVal = 0L;
	
	//����λռ��λ��
	private static final int FIRST_BIT = 1;
	//��������idռ��λ��
	private static final int WORK_BIT = 10;
	//���к�ռ��λ��
	private static final int ORDER_BIT = 12;
	
	private long workId;

	static {
		startTimeMills = LocalDateTime
				.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
				.toInstant(ZoneOffset.of("+8"))
				.toEpochMilli();
		
		MAX_WORK_NUM = -1L ^ (-1L << WORK_BIT);
		MAX_ORDER_NUM = -1L ^ (-1L << ORDER_BIT);
	}
	
	public Test_snowflake(long workId) {
		if(workId < 0L || workId > MAX_WORK_NUM) {
			throw new IllegalArgumentException("workId can't be greater than MAX_WORK_NUM or less than 0");
		}
		this.workId = workId;
	}
	
	public static void main(String[] args) throws Exception {
		Test_snowflake test = new Test_snowflake(1L);
		for(int i=0; i<1000; i++) {
			System.out.println(test.nextId());
		}
	}
	
	private long nextId() throws Exception {
		long currentTimeVal = getCurrentTime();

		if(currentTimeVal < lastTimeVal) {
			throw new Exception("ʱ�ӻز����޷�����");
		}
		else if(currentTimeVal == lastTimeVal) {
			
			if(lastOrderVal == MAX_ORDER_NUM) {
				while(currentTimeVal <= lastTimeVal) {
					currentTimeVal = getCurrentTime();
				}
				lastOrderVal = 0L;
			}
		}
		lastOrderVal += 1L;
		lastTimeVal = currentTimeVal;
		return currentTimeVal | workId << ORDER_BIT | lastOrderVal;
	}
	
	private long getCurrentTime() {
		long currentMillis = System.currentTimeMillis() - startTimeMills;
		return currentMillis << WORK_BIT << ORDER_BIT << FIRST_BIT >> FIRST_BIT;
	}
	
}
