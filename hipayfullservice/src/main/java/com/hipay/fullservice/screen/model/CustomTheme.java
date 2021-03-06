package com.hipay.fullservice.screen.model;

import android.os.Bundle;

import com.hipay.fullservice.core.mapper.AbstractMapper;
import com.hipay.fullservice.core.mapper.interfaces.BundleMapper;
import com.hipay.fullservice.core.models.AbstractModel;
import com.hipay.fullservice.core.serialization.AbstractSerializationMapper;

/**
 * Created by HiPay on 29/03/16.
 */
public class CustomTheme extends AbstractModel {

    private int colorPrimaryId;
    private int colorPrimaryDarkId;
    private int textColorPrimaryId;

    public static final String TAG = "theme";

    public CustomTheme() {
    }

    public static CustomTheme fromBundle(Bundle bundle) {

        CustomThemeMapper mapper = new CustomThemeMapper(bundle);
        return mapper.mappedObjectFromBundle();
    }

    public Bundle toBundle() {

        CustomTheme.CustomThemeSerializationMapper mapper = new CustomTheme.CustomThemeSerializationMapper(this);
        return mapper.getSerializedBundle();
    }

    public CustomTheme(int colorPrimaryId, int colorPrimaryDarkId, int textColorPrimaryId) {

        this.colorPrimaryId = colorPrimaryId;
        this.colorPrimaryDarkId = colorPrimaryDarkId;
        this.textColorPrimaryId = textColorPrimaryId;
    }

    public int getColorPrimaryId() {
        return colorPrimaryId;
    }

    public void setColorPrimaryId(int colorPrimaryId) {
        this.colorPrimaryId = colorPrimaryId;
    }

    public int getColorPrimaryDarkId() {
        return colorPrimaryDarkId;
    }

    public void setColorPrimaryDarkId(int colorPrimaryDarkId) {
        this.colorPrimaryDarkId = colorPrimaryDarkId;
    }

    public int getTextColorPrimaryId() {
        return textColorPrimaryId;
    }

    public void setTextColorPrimaryId(int textColorPrimaryId) {
        this.textColorPrimaryId = textColorPrimaryId;
    }

    protected static class CustomThemeSerializationMapper extends AbstractSerializationMapper {

        protected CustomThemeSerializationMapper(CustomTheme customTheme) {
            super(customTheme);
        }

        @Override
        protected String getQueryString() {

            return null;
        }

        @Override
        protected Bundle getSerializedBundle() {

            return super.getSerializedBundle();
        }
    }

    protected static class CustomThemeMapper extends AbstractMapper {
        public CustomThemeMapper(Bundle object) {
            super(object);
        }

        @Override
        protected boolean isValid() {

            if (this.getBehaviour() instanceof BundleMapper) {

                return true;
            }

            return true;
        }

        @Override
        protected CustomTheme mappedObject() {

            return null;
        }

        @Override
        protected CustomTheme mappedObjectFromBundle() {

            CustomTheme customTheme = new CustomTheme();

            customTheme.setColorPrimaryId(this.getIntegerForKey("colorPrimary"));
            customTheme.setColorPrimaryDarkId(this.getIntegerForKey("colorPrimaryDark"));
            customTheme.setTextColorPrimaryId(this.getIntegerForKey("colorTextPrimary"));

            return customTheme;
        }

        @Override
        protected Object mappedObjectFromUri() {
            return null;
        }
    }
}
