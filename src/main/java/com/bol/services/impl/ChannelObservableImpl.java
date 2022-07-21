package com.bol.services.impl;

import com.bol.services.ChannelObservable;
import com.bol.services.ChannelObserver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelObservableImpl implements ChannelObservable {

    public static Map<String, List<ChannelObserver>> channels = new HashMap<>();

    @Override
    public void subscribeToChannel(String channelName, ChannelObserver co) {
        if(channelName.isEmpty()){
            throw new RuntimeException("Channel Name can't be empty");
        }
        if (channels.containsKey(channelName)) {
            List<ChannelObserver> users = channels.get(channelName);
            if (!users.contains(co)) {
                users.add(co);
            } else {
                throw new RuntimeException("User already subscribed!");
            }
        } else {
            throw new RuntimeException("Channel Not Found!");
        }
    }

    private void notifyObservers(String channelName, String msg) {
        List<ChannelObserver> users = channels.get(channelName);
        users.forEach((observer) -> {
            observer.update(msg);
        });
    }

    @Override
    public void sendMessage(String channelName, String message) {
        notifyObservers(channelName,message);
    }

    @Override
    public void unSubscribe(String channelName, ChannelObserver co) {
        if (channels.containsKey(channelName)) {
            List<ChannelObserver> users = channels.get(channelName);
            if (users.contains(co)) {
                users.remove(co);
            } else {
                throw new RuntimeException("User not subscribed!");
            }
        }else {
            throw new RuntimeException("Channel Not Found!");
        }

    }

    @Override
    public void addChannel(String name) {
        if(name.isEmpty() || name == null){
            throw new RuntimeException("Channel Name can not be null or empty");
        }
       if(!channels.containsKey(name)){
           channels.put(name, new ArrayList<>());
       }else{
           throw new RuntimeException("Channel name already taken!");
       }
    }

    @Override
    public void removeChannel(String name) {
        if(name.isEmpty() || name == null){
            throw new RuntimeException("Channel Name can not be null or empty");
        }
        if(channels.containsKey(name)){
            channels.remove(name);
        }else{
            throw new RuntimeException("Channel Not Found!");
        }
    }

    @Override
    public boolean checkChannelName(String name) {
        return channels.containsKey(name);
    }
}
