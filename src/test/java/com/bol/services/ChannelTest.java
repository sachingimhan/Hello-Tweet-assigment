package com.bol.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class ChannelTest {

    Channel channel = null;

    @Before
    public void init(){
        channel = new Channel();
    }

    @Test
    public void subscribeToChannel() {
        LinkedList<String> users = channel.subscribeToChannel("Sachin");
        assertNotEquals(users,null);
    }

    @Test
    public void getSubCount(){
        int subCount = channel.getSubCount();
        assertEquals(subCount,0);
    }

}
