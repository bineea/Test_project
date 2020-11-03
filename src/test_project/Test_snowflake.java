package test_project;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 雪花算法-生成分布式ID
 * @author bineea
 *
 */
public class Test_snowflake {

	//开始时间
	private static final String startTime = "2015-06-01 00:00:00.000";
	private static final long startTimeMills;
	
	//工作机器id最大值
	private static final long MAX_WORK_NUM;
	//序列号最大值
	private static final long MAX_ORDER_NUM;
	
	//上一个id的时间戳对应值
	private static volatile long lastTimeVal = -1L;
	//上一个id的序列号对应值
	private static volatile long lastOrderVal = 0L;
	
	//符号位占用位数
	private static final int FIRST_BIT = 1;
	//工作机器id占用位数
	private static final int WORK_BIT = 10;
	//序列号占用位数
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
			throw new Exception("时钟回拨，无法处理");
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
