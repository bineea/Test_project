package test_project;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayMobilePublicMessageSingleSendRequest;
import com.alipay.api.response.AlipayMobilePublicMessageSingleSendResponse;

public class Test_alipaySingleSend 
{
	public static void main(String[] args)
	{
		Test_alipaySingleSend tas = new Test_alipaySingleSend();
		try
		{
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2016022901171309",
					"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPkz+vwsXYiszawDajJCZTq4QbBsIOo8RCuE2KhZW+oo/mTmUvzX8gjtLUIk43DP1dPXfkEMQWLKODM2Do3Hra4WEZArXu1OloukLoLyaNHNHSUq8N6QPHNKihiRtcBdYXQ2gvC3RFd9Wyp+zfbFx2k65g+x6NylM37bLbA8cVd7AgMBAAECgYEAqQI+4w23tbz0lAeQ21s6SlOygVMkE5gh553mTm6kLq/0sDug5sgR50nrmuNnuRfLfiu0/aqxIa3h5aFwx2k3+PVxBs9dJW1V2gIqLqGEuoIAHuUlp9WXIKi7yMxkhxLB0QqxNckaFfsLYqoJbx87o0EDFoCyu0XdgS1tvN+1rVECQQD/dHz4hZrJVZJfFVNll/cGwixixDwhl+HzlHwDVfDpAf8ZDdVmKkjlvh8zAoGuj2z6y/jXL+hj+JVBN9jWUP6zAkEA+bwT7YgT4aAyQwGrkahbs2h/APMy/iI5mQI9S8R/ixV5xwg8FO5nbECnqyyfQN5FG0FeBC3hnmw/5KV8jJGoGQJBAMvX4IFP25wdNJe7JLlE8lHqUBDgFjuYPy1npy3iPkMw3KbZaxgDVfg+JhdGQMVSndvjN8Y+7GoZysAoGFesehECQQC7EsWGyMnD2WHtSeIA0bInud5xkryfHHozATdze3XjdSntC2EeeNwmfmRbWKjPSfFQkm3gCZKaPxK5r7GWB0J5AkAEw3nCapE83recuCrYfyaYWONcTA0TA0m7VaRg0rF+QUxgsXiuFY51E2nfNcwTEknU/IsoNxZDrvL2g1ARAE/6",
					"json","GBK",
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
			
//			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2015080300197440",
//					"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKTckl3Y/93EEljiVvKSrsMwB94IvID0qSj6xh1q57sgYSnQf9pexx3pXiizrfy9pA+5113GzPNjnGk92yIcpHkhC0S9j+GPdZYLpu2Y8XkMrjtzZt0emy9DgaW6jnk9GNoE3FxuUS2849b0HlNjJvJr7PH5R5iWTxh8ZxtEy+rLAgMBAAECgYA1F9wDYUk1sFmASpRZ1qd1sNg3x3R9y01XJzv6aV1rxEfGYaFrQC68EVYDoaocxS466kzTpJyIncHXmtYhrDvDSmT3QPuz0ZOyzjYpilmRb6LbYmQx45t/GZCocCUD31JaVPZVFzT7RIQF6uIXy34GjTX7Nh/VELXPN44pxhs2AQJBANLTC16iVg84vk8LUWFP+LkIc8o1LdTEH1yscAyAAOhPVCoOOUjKJbKGPSInUjDtrWtd5tFbybUqbutiEacX3EsCQQDIMDLk+APOvnm8c1gX3O/s7vrOlN/VBMGt7OLNmWX2oyA4Shj85MKxB7hymsP/PAjTeuEv/I5rBJfwMXy+XhuBAkEAuJJLwe4dqfXjpxAhjzCUcGoV9VHZbgzF6DyPXGI0aS4KYUdSn4dauZEMtHn4Wx26VeU/zsqtLQ6ZTTnuentJQQJAZnHT+by6Jhjvr3LPiBa2aWe6zfD9wsNWYi0xu8lelqLQID47H1/qCjJLP8rXtkwAsEjX6Hi0YSvOnUqV7c23AQJBAMx5TJXrDv52R7z+fJ+CsPCLnAfQddZ6gG6DhhrRAT9OoC9fnQgOalPqdup7Ce0PEJbeu+jWSwn6V1jD6LrURG4=",
//					"json","GBK",
//					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
			AlipayMobilePublicMessageSingleSendRequest request = new AlipayMobilePublicMessageSingleSendRequest();
			request.setBizContent(tas.toSendOverTime());
			AlipayMobilePublicMessageSingleSendResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				System.out.println("调用成功"+response.getBody());
			} else {
				System.out.println("调用失败"+response.getBody());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("异常信息！"+e.getMessage());
		}
	}
	
	//郭文彬——2088702387802256
	//支付宝——2088612833373363
	
	public String toSendInvite()
	{
		String content = "{" +
				"    \"toUserId\":\"2088612833373363\"," +
				"    \"template\":{" +
				"      \"templateId\":\"618fa703dd1a475aa0c3b4271070de27\"" +
				"				  }" +
				"  			}";
		return content;
	}
	
	public String toSendNorm()
	{
		String content = "{" +
				"    \"toUserId\":\"2088702387802256\"," +
				"    \"template\":{" +
				"      \"templateId\":\"7528550df12e4a938ab576b2feaaee83\"," +
				"      \"context\":{" +
				"					\"headColor\":\"#85be53\"," +
				"					\"url\":\"\"," +
				"        			\"actionName\":\"\"," +
				"        			\"first\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"恭喜您获得增量指标！指标有效期为6个月，请在有效期内使用。\"" +
				"        						}," +
				"        			\"remark\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"\"" +
				"        						}," +
				"        			\"keyword1\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"json测试\"" +
				"        						}," +
				"        			\"keyword2\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"json增量指标通知\"" +
				"        						}," +
				"        			\"keyword3\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"2016-11-07\"" +
				"        						}," +
				"        			\"keyword4\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"2016-11-07\"" +
				"        						}" +
				"				   }" +
				"				  }" +
				"  			}";
		return content;
	}
	
	public String toSendOverTime()
	{
		String content = "{" +
				"    \"toUserId\":\"2088702387802256\"," +
				"    \"template\":{" +
				"      \"templateId\":\"b9954dfb4eea40869d4c9683d737264d\"," +
				"      \"context\":{" +
				"					\"headColor\":\"#85be53\"," +
				"					\"url\":\"\"," +
				"        			\"actionName\":\"\"," +
				"        			\"first\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"编码延期！！！\"" +
				"        						}," +
				"        			\"remark\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"\"" +
				"        						}," +
				"        			\"keyword1\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"json测试\"" +
				"        						}," +
				"        			\"keyword2\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"json增量指标通知\"" +
				"        						}" +
				"				   }" +
				"				  }" +
				"  			}";
		return content;
	}
	
	public String bjToSendNorm()
	{
		String content = "{" +
				"    \"toUserId\":\"2088702387802256\"," +
				"    \"template\":{" +
				"      \"templateId\":\"477af342db6249c181dbdb870ccdb7d4\"," +
				"      \"context\":{" +
				"					\"headColor\":\"#85be53\"," +
				"					\"url\":\"\"," +
				"        			\"actionName\":\"\"," +
				"        			\"first\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"恭喜您获得增量指标！指标有效期为6个月，请在有效期内使用。\"" +
				"        						}," +
				"        			\"remark\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"\"" +
				"        						}," +
				"        			\"keyword1\":{" +
				"          						\"color\":\"#85be53\"," +
				"          						\"value\":\"json测试\"" +
				"        						}" +
				"				   }" +
				"				  }" +
				"  			}";
		return content;
	}
}
