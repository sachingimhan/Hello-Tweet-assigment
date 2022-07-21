/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bol.services;

import com.bol.event.ChannelListener;

/**
 *
 * @author sachin
 */
public interface ChannelObservable{
    void addChannel(String name);
    void removeChannel(String name);
    boolean checkChannelName(String name);
    void subscribeToChannel(String channelName,ChannelObserver co);
    void sendMessage(String channelName, String message);
    void unSubscribe(String channelName, ChannelObserver co);
}
