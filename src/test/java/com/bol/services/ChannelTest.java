package com.bol.services;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ChannelTest {

    Channel channel = null;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        channel = new Channel();
    }

    @Test
    public void testTheSubUserReturn() {
        assertEquals("Sachin", channel.subscribeToChannel("Sachin"));
        assertNotEquals("Sachin", channel.subscribeToChannel("Dev"));
    }

    @Test
    public void testIfTheValuesNullOrEmpty(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Can't find the user name");
        channel.subscribeToChannel(null);
        channel.subscribeToChannel("");
    }

    @Test
    public void getSubCount() {
        channel.subscribeToChannel("Sachin");
        channel.subscribeToChannel("Dev");
        assertEquals(2, channel.getSubCount());
    }

    @Test
    public void unSubscribeToChannel(){
        channel.subscribeToChannel("Sachin");
        channel.subscribeToChannel("Dev");
        boolean isUnsubscribe = channel.unSubscribe("Sachin");
        assertEquals(true,isUnsubscribe);
    }

    @Test
    public void testUnsubscribeException(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("User Not Subscribe to this channel");
        channel.subscribeToChannel("Sachin");
        channel.subscribeToChannel("Dev");
        boolean isUnsubscribe = channel.unSubscribe(null); // null or empty
    }

}
