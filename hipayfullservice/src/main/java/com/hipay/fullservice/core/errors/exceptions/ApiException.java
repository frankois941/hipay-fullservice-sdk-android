package com.hipay.fullservice.core.errors.exceptions;

import android.os.Bundle;

import com.hipay.fullservice.core.mapper.AbstractMapper;
import com.hipay.fullservice.core.mapper.interfaces.BundleMapper;
import com.hipay.fullservice.core.mapper.interfaces.MapMapper;
import com.hipay.fullservice.core.serialization.AbstractSerializationMapper;
import com.hipay.fullservice.core.serialization.interfaces.AbstractSerialization;

import java.util.Map;

/**
 * Created by HiPay on 05/04/16.
 */
public class ApiException extends AbstractException {

    private Integer apiCode;

    private String description;

    public ApiException(String message, Integer statusCode, String description, Integer apiCode, Throwable throwable) {
        super(message, statusCode, throwable);
        this.apiCode = apiCode;
        this.description = description;
    }

    public static ApiException fromBundle(Bundle bundle) {

        ApiExceptionMapper mapper = new ApiExceptionMapper(bundle);
        return mapper.mappedObjectFromBundle();
    }

    public Bundle toBundle() {

        ApiException.ApiExceptionSerializationMapper mapper = new ApiException.ApiExceptionSerializationMapper(this);
        return mapper.getSerializedBundle();
    }

    protected static class ApiExceptionSerializationMapper extends AbstractSerializationMapper {

        protected ApiExceptionSerializationMapper(ApiException apiException) {
            super(apiException);
        }

        @Override
        protected String getQueryString() {
            return super.getQueryString();
        }

        @Override
        protected Bundle getSerializedBundle() {
            return super.getSerializedBundle();
        }
    }

    public static class ApiExceptionMapper extends AbstractMapper {

        public ApiExceptionMapper(Object rawData) {
            super(rawData);
        }

        @Override
        protected boolean isValid() {

            if (this.getBehaviour() instanceof MapMapper) {

                return true;

            } else if (getBehaviour() instanceof BundleMapper) {

                return true;
            }

            return true;
        }

        @Override
        protected ApiException mappedObject() {

            return null;
        }

        @Override
        protected ApiException mappedObjectFromBundle() {

            Bundle exceptionBundle = this.getBundleForKey("cause");
            HttpException httpException = null;
            if (exceptionBundle != null) {
                httpException = HttpException.fromBundle(exceptionBundle);
            }

            ApiException object = new ApiException(
                    this.getStringForKey("message"),
                    this.getIntegerForKey("code"),
                    this.getStringForKey("description"),
                    this.getIntegerForKey("apiCode"),
                    httpException
            );

            return object;
        }

        @Override
        protected Object mappedObjectFromUri() {
            return null;
        }
    }

    public static class ApiExceptionSerialization extends AbstractSerialization {

        public ApiExceptionSerialization(Exception exception) {
            super(exception);
        }

        @Override
        public Map<String, String> getSerializedRequest() {
            return null;
        }

        @Override
        public Bundle getSerializedBundle() {

            super.getSerializedBundle();

            ApiException apiException = (ApiException)this.getModel();

            this.putStringForKey("message", apiException.getMessage());
            this.putIntForKey("code", apiException.getStatusCode());
            this.putStringForKey("description", apiException.getDescription());
            this.putIntForKey("apiCode", apiException.getApiCode());

            Throwable exception = apiException.getCause();
            if (exception != null) {

                HttpException httpSubException = (HttpException) exception;
                Bundle bundle = httpSubException.toBundle();
                this.putBundleForKey("cause", bundle);
            }

            return this.getBundle();
        }

        @Override
        public String getQueryString() {
            return null;
        }
    }

    public Integer getApiCode() {
        return apiCode;
    }

    public String getDescription() {
        return description;
    }
}
