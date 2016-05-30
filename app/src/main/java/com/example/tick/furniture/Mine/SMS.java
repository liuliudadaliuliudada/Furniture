package com.example.tick.furniture.Mine;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tick on 2016/5/15.
 */
public class SMS extends BroadcastReceiver{
    String address,fullmessage;
    PhoneLogin phoneLogin;
    public SMS(PhoneLogin p) {
        this.phoneLogin = p;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullmessage() {
        return fullmessage;
    }

    public void setFullmessage(String fullmessage) {
        this.fullmessage = fullmessage;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        Object[] smsobj = (Object[]) bundle.get("pdus");
        for(Object ob:smsobj){
            msg = SmsMessage.createFromPdu((byte[]) ob);
        }
        //除了数字，其他都舍弃
        String mess = msg.getMessageBody().replaceAll("\\D+", "");
        phoneLogin.setEt_vcode(mess);
    }
}
