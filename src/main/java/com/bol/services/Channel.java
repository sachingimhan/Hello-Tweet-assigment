package com.bol.services;

import java.util.LinkedList;

public class Channel {

    LinkedList<String> sub = new LinkedList<String>();

    public String subscribeToChannel(String user) {
        if (user == null || user.isEmpty()) {
            throw new RuntimeException("Can't find the user name");
        }
        sub.add(user);
        return user;
    }


    public int getSubCount() {
        return sub.size();
    }

    public boolean unSubscribe(String user) throws RuntimeException {
        int i = sub.indexOf(user);
        if (i != -1) {
            sub.remove(i);
            return true;
        } else {
            throw new RuntimeException("User Not Subscribe to this channel");
        }
    }
}

