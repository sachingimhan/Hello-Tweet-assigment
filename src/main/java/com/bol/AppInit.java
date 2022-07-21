package com.bol;

import com.bol.services.ChannelObservable;
import com.bol.services.impl.ChannelObservableImpl;
import com.bol.view.Home;

public class AppInit {
    public static void main(String[] args) {
        ChannelObservable channelObservable = new ChannelObservableImpl();
        new Home(channelObservable).setVisible(true);
    }
}
