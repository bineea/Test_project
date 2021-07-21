package test_project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.LocationAwareValueMatcher;
import org.skyscreamer.jsonassert.ValueMatcherException;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * @author guowenbin9
 * 通过jsonassert实现json内容对比
 * 
 * https://github.com/skyscreamer/JSONassert
 */
public class Test_jsonValidator {

    public static void compareObject(Object excepted, Object actual) throws JSONException {
    	Test_jsonValidator.compareObject(JSON.toJSONString(excepted), JSON.toJSONString(actual));
    }

    public static void compareObject(String excepted, String actual) throws JSONException {
        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(excepted, actual,
                ComparatorFactory.createDefaultComparator());
        System.out.println(JSON.toJSONString(jsonCompareResult));
    }
	
	public static void main(String[] args) throws JSONException {
		String excepted = "{\"shopType\":\"NON\",\"orderNo\":\"187862978996\",\"secondEventName\":\"UPDATE_WAYBILL_TIME_INFO\",\"firstEventName\":\"UPDATE\",\"waybillNo\":\"JDLD10471357068\",\"waybillType\":1,\"context\":\"运单时效更新\",\"bizSource\":1,\"eclpOrderNo\":\"ESL123167283993529\",\"noticeTime\":1621699198252,\"ext\":{\"predictDeliverDate\":\"2021-05-27 00:00:00\"}}";
		String actual = "{\"shopType\":\"NON\",\"orderNo\":\"18786297899611\",\"secondEventName\":\"UPDATE_WAYBILL_TIME_INFO\",\"firstEventName\":\"UPDATE\",\"waybillNo\":\"JDLD10471357068\",\"waybillType\":1,\"context\":\"运单时效更新\",\"bizSource\":1,\"eclpOrderNo\":\"ESL123167283993529\",\"noticeTime\":1621699198252,\"ext\":{\"predictDeliverDate\":\"2021-05-27 00:00:00\"}}";

		Test_jsonValidator.compareObject(excepted, actual);
	}
}

class ComparatorFactory {
	public static CustomComparator createDefaultComparator() {
        List<Customization> customizations = createCustomizations();
        return new CustomComparator(JSONCompareMode.NON_EXTENSIBLE,
                customizations.toArray(new Customization[customizations.size()])
        );
    }

    public static List<Customization> createCustomizations () {
        List<Customization> customizations = new ArrayList<>();
        //忽略的属性
        customizations.addAll(createIgnoreFields(
                "id",
                "test",
                "createTime"
        ));

        customizations.addAll(createCompareNumericalFields(
                "age",
                "test"
        ));

        customizations.add(new Customization("remark", new LocationAwareValueMatcher<Object>() {

            @Override
            public boolean equal(Object o1, Object o2) {
                return false;
            }

            @Override
            public boolean equal(String prefix, Object actual, Object expected, JSONCompareResult result) throws ValueMatcherException {
            	try {
                    new CustomComparator(JSONCompareMode.NON_EXTENSIBLE).compareJSONArray(prefix, new JSONArray(expected.toString().split("-")), new JSONArray(actual.toString().split("-")), result);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }));

        return customizations;
    }

    private static List<Customization> createIgnoreFields(String... paths) {
        return Stream.of(paths)
                .map(path -> ignoreField(path))
                .collect(Collectors.toList());
    }

    private static Customization ignoreField(String path) {
        return new Customization(path, ((o1, o2) -> true));
    }

    private static List<Customization> createCompareNumericalFields(String... paths) {
        return Stream.of(paths)
                .map(path -> compareNumericalField(path))
                .collect(Collectors.toList());
    }

    private static Customization compareNumericalField(String path) {
        return new Customization(path, ((expected, actual) -> {
            if (actual == null && expected == null) {
                return true;
            }
            if (actual == null || expected == null) {
                return false;
            }

            String actualStr = actual.toString();
            String expectStr = expected.toString();

            if (StringUtils.isEmpty(actualStr) && StringUtils.isEmpty(expectStr)) {
                return true;
            }
            try {
                BigDecimal actualDecimal = new BigDecimal(String.valueOf(actual));
                BigDecimal expectedDecimal = new BigDecimal(String.valueOf(expected));
                return actualDecimal.compareTo(expectedDecimal) == 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }));
    }
}
