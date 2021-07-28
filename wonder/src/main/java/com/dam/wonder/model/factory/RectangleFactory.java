package com.dam.wonder.model.factory;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 静态工厂
 */
public class RectangleFactory {
  public static Rectangle constructorRectangleAuto (String type, Double height, Double width) {
      if (type.equals("heightLight")) {
          return  new javafx.scene.shape.Rectangle(width, height, Color.color(0.7, 0.9, 0.8, 0.5));
      }else {
          return null;
      }
  }
}
