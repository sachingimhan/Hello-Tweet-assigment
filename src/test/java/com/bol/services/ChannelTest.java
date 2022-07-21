package com.bol.services;

import com.bol.services.impl.ChannelObservableImpl;
import com.bol.view.Subscriber;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ChannelTest {

    ChannelObservableImpl channel = null;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        channel = new ChannelObservableImpl();
    }

    @Test
    public void testIfUserCanCreateChannel(){
        channel.addChannel("a");
        assertEquals(1,ChannelObservableImpl.channels.size());
    }

    @Test
    public void testIfUserGetExceptionWhenCreatingChannelWithEmptyOrNullChannelName(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Name can not be null or empty");
        channel.addChannel("");
        channel.addChannel(null);
    }

    @Test
    public void testIfUseGetExceptionWhenCreatingChannelWithDuplicateChannelName(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel name already taken!");
        channel.addChannel("a");
        channel.addChannel("a");
    }

    @Test
    public void testIfUserCanRemoveChannel(){
        channel.addChannel("a");
        assertEquals(1,ChannelObservableImpl.channels.size());
        channel.removeChannel("a");
        assertEquals(0,ChannelObservableImpl.channels.size());
    }

    @Test
    public void testIfUserGetExceptionWhenRemovingChannelWithEmptyOrNullChannelName(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Name can not be null or empty");
        channel.addChannel("a");
        System.out.println(ChannelObservableImpl.channels.size());
        channel.removeChannel("");
        channel.removeChannel(null);
    }

    @Test
    public void testIfUserCanSubscribeToTheChannel() {
        channel.addChannel("a");
        channel.subscribeToChannel("a", new Subscriber());
        assertEquals(1, ChannelObservableImpl.channels.get("a").size());
    }

    @Test
    public void testIfUserGetExceptionWhenSubscribeToChannelWithEmptyOrNullChannelName(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Name can't be empty");
        channel.subscribeToChannel("",new Subscriber());
        channel.subscribeToChannel(null,new Subscriber());
    }

    @Test
    public void testIfUseGetExceptionWhenSubscribeToSameChannelAgain(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("User already subscribed!");
        channel.addChannel("a");
        ChannelObserver subscriber = new Subscriber();
        channel.subscribeToChannel("a",subscriber);
        channel.subscribeToChannel("a",subscriber);
    }

    @Test
    public void testIfUseGetExceptionWhenSubscribeToNonExistingChannel(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Not Found!");
        ChannelObserver subscriber = new Subscriber();
        channel.subscribeToChannel("a",subscriber);
    }

    @Test
    public void testIfUserCanUnsubscribeToTheChannel() {
        channel.addChannel("a");
        channel.addChannel("b");
        ChannelObserver subscriber = new Subscriber();
        channel.subscribeToChannel("a", subscriber);
        channel.subscribeToChannel("b", subscriber);
        channel.unSubscribe("a",subscriber);
        assertEquals(0, ChannelObservableImpl.channels.get("a").size());
    }

    @Test
    public void testIfUserGetExceptionWhenUnsubscribeToChannelWithEmptyOrNullChannelName(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Not Found!");
        channel.addChannel("a");
        channel.addChannel("b");
        ChannelObserver subscriber = new Subscriber();
        channel.subscribeToChannel("a",subscriber);
        channel.unSubscribe("",subscriber);
        channel.unSubscribe(null,subscriber);
    }

    @Test
    public void testIfUseGetExceptionWhenUnsubscribeToSameChannelAgain(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("User not subscribed!");
        channel.addChannel("a");
        ChannelObserver subscriber = new Subscriber();
        channel.subscribeToChannel("a",subscriber);
        channel.unSubscribe("a",subscriber);
        channel.unSubscribe("a",subscriber);
    }

    @Test
    public void testIfUseGetExceptionWhenUnsubscribeToNonExistingChannel(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Channel Not Found!");
        ChannelObserver subscriber = new Subscriber();
        channel.addChannel("a");
        channel.subscribeToChannel("a",subscriber);
        channel.unSubscribe("b",subscriber);
    }

    @Test
    public void testIfChannelNameAlreadyTaken(){
        channel.addChannel("a");
        boolean a = channel.checkChannelName("a");
        assertTrue(a);
    }

    @Test
    public void testIfSubscriberReceiveChannelMessage(){
        channel.addChannel("a");
        ChannelObserver subscriber = a -> {
            System.out.println(a);
            assertEquals("Test Message",a);
        };
        channel.subscribeToChannel("a",subscriber);
        channel.sendMessage("a","Test Message");
    }

}
