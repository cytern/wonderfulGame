package com.dam.wonder;

import com.dam.wonder.model.pojo.item.Item;
import com.dam.wonder.model.pojo.item.Talk;

public class ItemTest {

    public static void main(String[] args) {
        Item item = new Item();
        System.out.println(item.getType());
        Talk talk = new Talk();
        System.out.println(talk.getType());
        Item item1 = talk;
        System.out.println(item1.getType());
    }
}
