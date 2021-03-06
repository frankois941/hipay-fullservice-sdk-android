package com.hipay.fullservice.core.serialization.interfaces;

import android.os.Bundle;

import com.hipay.fullservice.core.serialization.BundleSerialization;
import com.hipay.fullservice.core.serialization.IBundle;

import java.net.URL;
import java.util.Date;
import java.util.Map;

/**
 * Created by HiPay on 04/02/16.
 */
public abstract class AbstractSerialization<T> implements ISerialization {

    protected T model;

    IBundle bundleBehaviour;

    public AbstractSerialization(T model) {
        this.setModel(model);
    }

    public abstract Map<String, String> getSerializedRequest();

    public Bundle getSerializedBundle() {

        this.setBundleBehaviour(new BundleSerialization());
        return this.getBundle();
    }

    public abstract String getQueryString();

    private IBundle getBundleBehaviour() {
        return bundleBehaviour;
    }

    private void setBundleBehaviour(IBundle bundleBehaviour) {
        this.bundleBehaviour = bundleBehaviour;
    }

    protected T getModel() {
        return model;
    }

    private void setModel(T model) {
        this.model = model;
    }

    protected void putFloatForKey(String key, Float floatNumber) {
        this.getBundleBehaviour().putFloat(key, floatNumber);
    }

    protected void putStringForKey(String key, String string) {
        this.getBundleBehaviour().putString(key, string);
    }

    protected void putUrlForKey(String key, URL url) {
        this.getBundleBehaviour().putUrl(key, url);
    }

    protected void putIntForKey(String key, Integer integer) {
        this.getBundleBehaviour().putInt(key, integer);
    }

    protected void putDateForKey(String key, Date date) {
        this.getBundleBehaviour().putDate(key, date);
    }

    protected void putBoolForKey(String key, Boolean bool) {
        this.getBundleBehaviour().putBool(key, bool);
    }

    protected void putBundleForKey(String key, Bundle bundle) {
        this.getBundleBehaviour().putBundle(key, bundle);
    }

    protected void putMapJSONForKey(String key, Map<String, String> map) {
        this.getBundleBehaviour().putMapJSON(key, map);
    }

    protected Bundle getBundle() {
       return this.getBundleBehaviour().getBundle();
    }
}