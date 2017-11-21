package test_project;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

public class Test_alipayOauthResponse {
	public static void main(String[] args)
	{
		try
		{
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2014092300012237",
					"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPPFwiwcHd716XHiZi2cXCoauNL4LHnVtLMyr7W4uN6yLiIoCTIjOIHaLWqRw8emVDKZ1a35u0sURNKTS9da1Qwk9pN+gxhTasiVKLKTrQH6S1YMNQHt9+PGH8noE/KuqwcLFqUSFB9tpnrBB91fBdCmRPvdE8xv10bsi/CIhpRFAgMBAAECgYAbAC2IqziXyI8p4RTGOopu03TjUT/D4urX6mZR73ryl7PKbrrOBd6ta1gadLjFtWOgEy/6zC11nHIfDNeaq+Hq9hiGMC6VG0Pc4W+yUIgdXq6JQ+8gCZj/ccmbc69tLZ9Dea2V4H1tXBr7o6aU/F+Eqgdo4CY7XDg08Pq31LjjpQJBAP2SiopbtCTNFWxRKb8YVVtI3k3ejsf1rDnRpqbNiOGcL04JLOJX2PvpLl0p+HzwOgqxNDih5eUvEsk4c/tkJRcCQQD2GzMShsb4GFEJk01u9m/LIaUvfoyD0QTFk4ir2trmL/1nvZs9IELdFCtAoK8jc2VkPc2nOilSEck9DCYh+CMDAkEA9BnKtlB7RWYLtn8XcfdJzBuErW+PAZ1VUz+obzCzEYSZ4URh6e5pWm/qCxNpO1TCeyKFQ/NObWb4ba6jsCCCtwJBAJFIPT4SkiKjODOO4Yc/s38WvYzsB8dI/KgmD+hjqhV4/v7ez2WmRdJ8tYvRT5J97LzgPycZDouENGf1ZA1pjkECQDSczALyv3kqKFylfjgARGvW9TAx0C+Fulc5KXg0mj3G+g4jYB73qrcTlARKkGdvRQyVeXD55Ojtn53l3+P31GI=",
					"json","GBK",
					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
			
//			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2016022901171309",
//					"MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPkz+vwsXYiszawDajJCZTq4QbBsIOo8RCuE2KhZW+oo/mTmUvzX8gjtLUIk43DP1dPXfkEMQWLKODM2Do3Hra4WEZArXu1OloukLoLyaNHNHSUq8N6QPHNKihiRtcBdYXQ2gvC3RFd9Wyp+zfbFx2k65g+x6NylM37bLbA8cVd7AgMBAAECgYEAqQI+4w23tbz0lAeQ21s6SlOygVMkE5gh553mTm6kLq/0sDug5sgR50nrmuNnuRfLfiu0/aqxIa3h5aFwx2k3+PVxBs9dJW1V2gIqLqGEuoIAHuUlp9WXIKi7yMxkhxLB0QqxNckaFfsLYqoJbx87o0EDFoCyu0XdgS1tvN+1rVECQQD/dHz4hZrJVZJfFVNll/cGwixixDwhl+HzlHwDVfDpAf8ZDdVmKkjlvh8zAoGuj2z6y/jXL+hj+JVBN9jWUP6zAkEA+bwT7YgT4aAyQwGrkahbs2h/APMy/iI5mQI9S8R/ixV5xwg8FO5nbECnqyyfQN5FG0FeBC3hnmw/5KV8jJGoGQJBAMvX4IFP25wdNJe7JLlE8lHqUBDgFjuYPy1npy3iPkMw3KbZaxgDVfg+JhdGQMVSndvjN8Y+7GoZysAoGFesehECQQC7EsWGyMnD2WHtSeIA0bInud5xkryfHHozATdze3XjdSntC2EeeNwmfmRbWKjPSfFQkm3gCZKaPxK5r7GWB0J5AkAEw3nCapE83recuCrYfyaYWONcTA0TA0m7VaRg0rF+QUxgsXiuFY51E2nfNcwTEknU/IsoNxZDrvL2g1ARAE/6",
//					"json","GBK",
//					"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB");
			
			AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
			request.setCode("512100f9a8684d3b8bf7b82cd469QC25");
			request.setGrantType("authorization_code");
			AlipaySystemOauthTokenResponse response = alipayClient.execute(request);//2088702387802256
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

}
