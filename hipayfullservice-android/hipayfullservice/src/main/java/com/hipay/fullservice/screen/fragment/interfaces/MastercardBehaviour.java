package com.hipay.fullservice.screen.fragment.interfaces;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hipay.fullservice.R;
import com.hipay.fullservice.core.models.PaymentProduct;
import com.hipay.fullservice.screen.helper.FormHelper;

/**
 * Created by nfillion on 20/05/16.
 */
public class MastercardBehaviour implements ICardBehaviour {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void updateForm(EditText cardNumber, EditText cardCVV, EditText cardExpiry, TextInputLayout securityCodeLayout, TextView securityCodeInfoTextview, ImageView securityCodeInfoImageview, LinearLayout switchLayout, boolean networked, Context context) {

        securityCodeLayout.setVisibility(View.VISIBLE);
        cardNumber.setHint(context.getString(R.string.card_number_placeholder_visa_mastercard));

        cardExpiry.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        cardCVV.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        cardCVV.setHint(context.getString(R.string.card_security_code_placeholder_cvv));

        cardNumber.setFilters( new InputFilter[] { new InputFilter.LengthFilter(FormHelper.getMaxCardNumberLength(PaymentProduct.PaymentProductCodeMasterCard, context))});

        int card = networked?R.drawable.ic_credit_card_cb:R.drawable.ic_credit_card_mastercard;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cardNumber.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, card, 0);
        } else {
            cardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, card, 0);
        }

        securityCodeInfoTextview.setText(context.getString(R.string.card_security_code_description_cvv));
        securityCodeInfoImageview.setImageResource(R.drawable.cvc_mv);

        switchLayout.setVisibility(isCardStorageEnabled() ? View.VISIBLE: View.GONE);
        //Mastercard
        //"5399 9999 9999 9999",
        //cardNumber.setText("5399999999999999");
        //cardCVV.setText("123");
    }

    @Override
    public boolean isSecurityCodeValid(EditText cardCVV) {

        if (!TextUtils.isEmpty(cardCVV.getText())) {

            String cvvString = cardCVV.getText().toString().trim();
            if (cvvString.length() == 3 && TextUtils.isDigitsOnly(cvvString)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasSecurityCode() {
        return true;
    }

    @Override
    public boolean hasSpaceAtIndex(Integer index, Context context) {

        return FormHelper.isIndexSpace(index, PaymentProduct.PaymentProductCodeMasterCard, context);
    }

    @Override
    public String getProductCode() {
        return PaymentProduct.PaymentProductCodeMasterCard;
    }

    @Override
    public boolean isCardStorageEnabled() {
        return true;
    }
}
