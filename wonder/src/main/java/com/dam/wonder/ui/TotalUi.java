package com.dam.wonder.ui;

/**
 * 总体ui
 */
public class TotalUi {

    private TotalUi() {

    };

    private static TotalUi instance;

    public synchronized TotalUi getInstance() {
        if (instance == null) {
            instance = new TotalUi();
        }
        return instance;
    }

}
