package com.dam.wonder.factory;

import com.dam.wonder.pojo.Talk;

import java.util.ArrayList;
import java.util.List;

/**
 * 对话组装工厂
 */
public class TalkFactory {

    public static List<Talk> buildTalkList() {
        List<Talk> talkList = new ArrayList<>();
        Talk talk = new Talk();


        talk.setTalk("嘤嘤嘤嘤嘤");
        talk.setAsk(true);
        talk.setShowDrawUrl("assets/textures/drawing/green/5146.png");
        talkList.add(talk);

        Talk talk1 = new Talk();
        talk1.setTalk("嘤嘤嘤嘤");
        talk1.setAsk(false);
        talk1.setShowDrawUrl("assets/textures/drawing/wan/t4-1.png");
        talkList.add(talk1);
        return talkList;
    }
}
