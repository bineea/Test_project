package org.skyscreamer.jsonassert.comparator;

import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.allJSONObjects;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.allSimpleValues;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.getKeys;
import static org.skyscreamer.jsonassert.comparator.JSONCompareUtil.qualify;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class MissIgnoreComparator extends CustomComparator {

    private List<String> ignoreFields;

    public MissIgnoreComparator(JSONCompareMode mode) {
        super(mode);
    }

    public MissIgnoreComparator(JSONCompareMode mode, Customization... customizations) {
        super(mode, customizations);
    }

    public MissIgnoreComparator(JSONCompareMode mode, List<String> ignoreFields, Customization... customizations) {
        super(mode, customizations);
        this.ignoreFields = ignoreFields;
    }

    @Override
    protected void checkJsonObjectKeysActualInExpected(String prefix, JSONObject expected, JSONObject actual, JSONCompareResult result) {
        Set<String> actualKeys = getKeys(actual);
        for (String key : actualKeys) {
            if (ignoreFields != null && ignoreFields.contains(qualify(prefix, key))) {
                continue;
            }
            if (!expected.has(key)) {
                Object actualValue = null;
                try {
                    actualValue = actual.get(key);
                } catch (JSONException ignore) {
                    ignore.printStackTrace();
                }

                result.unexpected(qualify(prefix, key), actualValue);
            }
        }
    }

    @Override
    protected void checkJsonObjectKeysExpectedInActual(String prefix, JSONObject expected, JSONObject actual, JSONCompareResult result) throws JSONException {
        Set<String> expectedKeys = getKeys(expected);
        for (String key : expectedKeys) {
            Object expectedValue = expected.get(key);
            if (ignoreFields != null && ignoreFields.contains(qualify(prefix, key))) {
                continue;
            }
            if (actual.has(key)) {
                Object actualValue = actual.get(key);
                compareValues(qualify(prefix, key), expectedValue, actualValue, result);
            } else {
                result.missing(qualify(prefix, key), expectedValue);
            }
        }
    }

    @Override
    public void compareJSONArray(String prefix, JSONArray expected, JSONArray actual, JSONCompareResult result)
            throws JSONException {
        if (expected.length() != actual.length()) {
            result.fail(prefix, expected.toString(), actual.toString());
            return;
        } else if (expected.length() == 0) {
            return; // Nothing to compare
        }

        if (mode.hasStrictOrder()) {
            compareJSONArrayWithStrictOrder(prefix, expected, actual, result);
        } else if (allSimpleValues(expected)) {
            compareJSONArrayOfSimpleValues(prefix, expected, actual, result);
        } else if (allJSONObjects(expected)) {
            compareJSONArrayOfJsonObjects(prefix, expected, actual, result);
        } else {
            // An expensive last resort
            recursivelyCompareJSONArray(prefix, expected, actual, result);
        }
    }
}
