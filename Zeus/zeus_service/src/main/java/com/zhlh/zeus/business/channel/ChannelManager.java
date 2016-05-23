package com.zhlh.zeus.business.channel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ChannelManager {
    private static HashMap<String, Channel> channelBases = new HashMap<String, Channel>();

    public static void initialize(String filepath) {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filepath), "UTF-8"));

            Gson gsonMaker = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC).create();

            Type type = new TypeToken<ArrayList<Channel>>() {
            }.getType();
            ArrayList<Channel> list = gsonMaker.fromJson(in, type);
            for (Channel one : list) {
                if (one != null) {
                    one.pack();
                    channelBases.put(one.code, one);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Channel getChannel(String channelCode) {
        return channelBases.get(channelCode);
    }

    public static boolean isChannelAvailable(String channelCode) {
        Channel one = channelBases.get(channelCode);
        if (one == null) {
            return false;
        }

        if (one.restricts.length <= 0) {
            return false;
        }
        return true;
    }

}
