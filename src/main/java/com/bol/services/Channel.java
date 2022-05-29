package com.bol.services;

import java.util.LinkedList;

public class Channel {

    LinkedList<String> sub = new LinkedList<String>();

    public LinkedList<String> subscribeToChannel(String user){
        sub.add(user);
        return sub;
    }


    public int getSubCount() {
        return sub.size();
    }
}

