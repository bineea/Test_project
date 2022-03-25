package test_project.basic.http;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test_http_request {
	
	
	private String doHttpPost(String url, String topicName, String appName, String key) {
		HttpPost post = new HttpPost(url);

		post.setHeader("Host","jmq.jd.com");
		post.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		post.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		post.setHeader("Cookie", "jd.erp.lang=zh_CN; jdd69fo72b8lfeoe=56YS3QRRY6D3RJH2CHDFBCV5VJIYEIRLMEEEV5GMSEGLKPSSRCL4D2GDROFPK7GVJTFH4CTNLFBESSPAWDVCNL424E; __jda=101385626.16479195548671479660121.1647919555.1647919555.1648000383.2; __jdv=64431794|direct|-|none|-|1647919554867; __jdu=16480398668991036138223; JSESSIONID=847FF092605C77AA39002F283AB7B21F.s1; sso.jd.com=BJ.10EB96EC12CF3DFE1CACBC2F706DF4647820220325165350; jmq.principal=8NenM4qEoXs926P5oJa9yASjYJ5wrXROdHcXZApb6vwUallY5b9xDboPPrUT4nn8");
		post.setHeader("Connection","keep-alive");
		
		List<NameValuePair>list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("_search", "true"));
		list.add(new BasicNameValuePair("nd", "1648198238770"));
		list.add(new BasicNameValuePair("pageSize", "10"));
		list.add(new BasicNameValuePair("currentPage", "1"));
		list.add(new BasicNameValuePair("sord", "asc"));
		list.add(new BasicNameValuePair("totalrows", "2000"));
		list.add(new BasicNameValuePair("topicQuery", topicName));
		list.add(new BasicNameValuePair("appQuery", appName));
		list.add(new BasicNameValuePair("mode", "1"));
		
		post.setEntity(new UrlEncodedFormEntity(list, StandardCharsets.UTF_8));
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();

		CloseableHttpClient client = clientBuilder.build();
		try {
			HttpResponse response = client.execute(post);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("状态码为："+response.getStatusLine().getStatusCode());
			System.out.println("返回内容为："+result);
			
			JSONObject jsonObj = JSON.parseObject(result);
			JSONArray resultArray = jsonObj.getJSONArray("result");
			for (int i=0; i<resultArray.size(); i++) {
				String topic = resultArray.getJSONObject(i).getString("topic");
				String app = resultArray.getJSONObject(i).getString("app");
				if (Objects.equals(topicName, topic) && Objects.equals(appName, app)) {
					return resultArray.getJSONObject(i).getString(key);
				}
			}

			return null;
		} catch(Exception ex) {
			ex.printStackTrace();
			//throw new RuntimeException(ex);
		}
		return null;
	}
	
	private String doHttpGet(String url, String topicId, String topicName, String appName, String erp, String key) {
		HttpGet get = new HttpGet(url + "?topicId="+topicId+"&topic="+topicName+"&app="+appName+"&erp="+erp);
		
		get.setHeader("Host","jmq.jd.com");
		get.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
		get.setHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		get.setHeader("Cookie", "jd.erp.lang=zh_CN; jdd69fo72b8lfeoe=56YS3QRRY6D3RJH2CHDFBCV5VJIYEIRLMEEEV5GMSEGLKPSSRCL4D2GDROFPK7GVJTFH4CTNLFBESSPAWDVCNL424E; __jda=101385626.16479195548671479660121.1647919555.1647919555.1648000383.2; __jdv=64431794|direct|-|none|-|1647919554867; __jdu=16480398668991036138223; JSESSIONID=847FF092605C77AA39002F283AB7B21F.s1; sso.jd.com=BJ.10EB96EC12CF3DFE1CACBC2F706DF4647820220325165350; jmq.principal=8NenM4qEoXs926P5oJa9yASjYJ5wrXROdHcXZApb6vwUallY5b9xDboPPrUT4nn8");
		get.setHeader("Connection","keep-alive");
		
		HttpClientBuilder clientBuilder = HttpClientBuilder.create();
		CloseableHttpClient client = clientBuilder.build();
		try {
			HttpResponse response = client.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println("状态码为："+response.getStatusLine().getStatusCode());
			System.out.println("返回内容为："+result);
			
			JSONObject jsonObj = JSON.parseObject(result);
			JSONObject resultObj = jsonObj.getJSONObject("result");

			return resultObj.getString(key);
		} catch(Exception ex) {
			ex.printStackTrace();
			//throw new RuntimeException(ex);
		}
		return null;
	}
	
	private Map<String, Long> sortMapByValue(Map<String, Long> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
        List<Map.Entry<String, Long>> entryList = new ArrayList<Map.Entry<String, Long>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Long>> iter = entryList.iterator();
        Map.Entry<String, Long> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
	
	static class MapValueComparator implements Comparator<Map.Entry<String, Long>> {

	    @Override
	    public int compare(Entry<String, Long> me1, Entry<String, Long> me2) {

	        return me1.getValue().compareTo(me2.getValue());
	    }
	}
	
	public static void main(String[] args)
	{
		Test_http_request test = new Test_http_request();
		
		String topicNameStr = "purchase_lbmw_topic,purchase_extra_lbmw_topic,purchase_cancel_lbmw_topic,rejection_lbmw_topic,owner_lbmw_topic,vendor_lbmw_topic,return_vendor_lbmw_topic,return_vendor_cancel_lbmw_topic,pickup_order_lbmw_topic,waybill_transport_lbmw_topic,service_order_lbmw_topic,service_outorin_lbmw_topic,return_seller_lbmw_topic,check_store_lbmw_topic,order_status_lbmw_topic,mid_order_pool_replenish_wms,order_lbmw_topic,mid_waybill_update_olp,order_cancel_lbmw_topic,LASDD_LBMW_ORDERBACK,sendDeliveryWaybill,LASDD_LBMW_DISPATCH,laswms_rpc_orderStatus,eclp_so_status_wms,eclp_order_cancel,eclp_leave_msg_back,LASDD_LBMW_RESERVE_NOTICE,LASDD_LBMW_RESERVE,ORDER_RESERVE_MESSAGE,mid_waybill_rejectionaffirm_olp,lbmw_order_status,lbmw_order_cancel,olp_self_purchase_status_mid,waybill_forward_pipeline_topic,waybill_cancel_pipeline_topic,waybill_rejection_affirm_pipeline_topic,olp_returnwaybill_mid,olp_eclp_waybill_cancelResult_mid,olp_waybill_status_mid,olp_open_purchase_status_mid,mid_waybill_cancelSec_olp,mid_warehouseDeliveryInstall_im,mid_warehouseDeliveryInstallCancel_im,internal_send_es_order,LASTMS_WAYBILL_1,LASTMS_WAYBILL_3,LASTMS_WAYBILL_6,LASTMS_WAYBILL_7,LASTMS_DUIZHANG_REDIRECT_ECLP,eclp_order_confirm_back,finance_order_confirm_back,LASTMS_DUIZHANG_REDIRECT_ECLP2OPEN,mid_waybill_forward_olp,mid_waybill_forward_olp_UAT,mid_waybill_cancel_olp,mid_waybill_forward_eclp,olpmid_waybill_return_eclp,mid_order_forward_waybill_adapter,mid_waybill_statusReturn_eclp,mid_carryinformation_tc,lbmw_finance_wms_topic,lbmw_finance_hmas_topic,mid_order_lahui_mads,mid_order_lahui_olp,las_half_receive,mid_half_apply_eclp,mid_order_pipeline_pool,order_origin_wh_topic,order_origin_wh_cancel_topic,producingWhOrderToMads,mid_site_warehouse_hfs_laim,PRODUCING_WH_PRESORTED,mid_site_warehouse_status_laim,lasim_mid_ow_confirm_cancel,lasim_mid_ow_status,PRODUCING_WH_PLAN,mid_site_warehouse_cancel_laim,mid_order_lahui_im,LASTMS_WAYBILL_60,eclp_rejectAuditResult_mid,rejectAuditResult_mid,mid_rejectAudit_eclp,mid_rejectAudit_olp,mid_rejectAuditResult_tms,olpmid_waybill_return_clps,mid_transportInfo_deliver_promise,mid_transportInfo_deliver_cancel_promise,las_tms_delivery_card_back_1,las_tms_delivery_card_back_2,mid_delivery_card_back_warehouse_eclp,mid_delivery_card_back_merchant_eclp,mid_waybill_cancel_tms,LAS_ORDER_CANCEL_OPEN,mid_order_lot_eclp,laswms_rpc_orderLotLibrary,mid_wms_lot_eclp,wms3_eclp_order_deliver,WMS_ECLP_INV_LOT_CHANGE,mid_lot_modulation_eclp,las_delivery_eclp,realright_transfer_lbmw_topic,realright_transfer_cancel_lbmw_topic,mid_realright_transfer_wms,mid_realright_transfer_status_eclp,wms_realright_transfer_status_mid,mid_realright_transfer_finish_eclp,wms_realright_transfer_finish_mid,wms_realright_transfer_cancel_result_mid,mid_realright_transfer_cancel_wms,mid_realright_transfer_cancel_result_eclp,POP_OUT_STORAGE,im_orderno_status,full_tracking_2201,full_tracking_2202,full_tracking_2203,full_tracking_2204,full_tracking_2205,full_tracking_2206,full_tracking_2207,full_tracking_2208,full_tracking_2209,0_200,0_300,0_2,0_5,0_6,0_90,0_87,order_gps_path,mid_open_order_status,mid_install_status,waybill_rejection_instore_pipeline_topic,mid_waybill_rejection_instore_olp,waybill_update_pipeline_topic,mid_waybill_update_olp,las_tms_delivery_card_back_elec,las_tms_delivery_card_back_paper,mid_delivery_card_back_elec,mid_delivery_card_back_paper,mid_wd_status,olp_waybill_info_change_tc,las_mid_common_message,mads_b2b_answer_mid,order_package_to_middleware,package_damage_status_to_middleware,olp_waybill_tc,tms_service_feedback,lsc_service_bill_receivable,mid_service_feedback,mid_status_olp,olp_order_update_mid,olp_waybill_package_change_mid,lcdp_waybill_status,accounting_mid,mads_change_production_time_lbmw,LASDD_TRANSERFER_TIME,order_lppw_topic,olp_waybill_special_broadcast,lbmw_waybill_forward_olp,lbmw_order_down_wms,lbmw_order_pool_replenish_wms,lbmw_pickup_order_lbmw_topic,eclp:1.0.0,3cs_recycle_order_las_mid,olp_old_change_new_info,3cs_recycle_order_update_las_mid,3cs_recycle_order_cancel_las_mid,mid_recycle_olp,mid_old_change_new_reset,mid_recycle_update_olp,mid_recycle_cancel_olp";
		String[] topicNames = topicNameStr.split(",");
		Map<String, Long> topicEnQueue = new HashMap<>();
		for (int i=0; i<topicNames.length; i++) {
			String topicName = topicNames[i];
			
			
			String producerSearch = "http://172.20.35.70/producerMonitor/search.do";
			String producerOverview = "http://172.20.35.70/producerMonitor/getProducerOverview.do";
			String consumerSearch = "http://172.20.35.70/consumerMonitor/search.do";
			String consumerOverview = "http://172.20.35.70/consumerMonitor/getConsumerOverview.do";
			
			long enQueue = 0L;
			String topicIdProducer = test.doHttpPost(producerSearch, topicName, "lasmiddleware", "topicId");
			if (topicIdProducer != null && !Objects.equals(topicIdProducer, "")) {
				String enQueueProducer = test.doHttpGet(producerOverview, topicIdProducer, topicName, "lasmiddleware", "zhengbing5", "enQueue");
				enQueue += Long.valueOf(enQueueProducer);
			}
			
			String topicIdConsumer = test.doHttpPost(consumerSearch, topicName, "lasmiddleware", "topicId");
			if (topicIdConsumer != null && !Objects.equals(topicIdConsumer, "")) {
				String enQueueConsumer = test.doHttpGet(consumerOverview, topicIdConsumer, topicName, "lasmiddleware", "zhengbing5", "deQueue");
				enQueue += Long.valueOf(enQueueConsumer);
			}
		
			
			topicEnQueue.put(topicName, enQueue);
		}
		
		Map<String, Long> sortedMap = test.sortMapByValue(topicEnQueue);
		System.out.println(JSON.toJSONString(sortedMap));
	}
}
