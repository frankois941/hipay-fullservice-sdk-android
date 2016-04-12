package com.hipay.hipayfullservice.core.client.interfaces;

import android.content.Context;
import android.os.Bundle;

import com.hipay.hipayfullservice.core.errors.exceptions.HttpException;
import com.hipay.hipayfullservice.core.network.HttpResult;
import com.hipay.hipayfullservice.core.operations.AbstractOperation;
import com.hipay.hipayfullservice.core.operations.GatewayOperation;

import org.json.JSONObject;

/**
 * Created by nfillion on 22/02/16.
 */
public interface IReqHandler {

    String getReqQueryString();
    AbstractOperation getReqOperation(Context context, Bundle bundle);
    void handleCallback(HttpResult result);
    int getLoaderId();
    void onError(HttpException exception);
    void onSuccess(JSONObject jsonObject);

}