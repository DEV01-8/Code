/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class DEV018 extends PApplet {

    JSONObject json;
    // ArrayLists to put extracted values in from JSONArray.
    ArrayList<Point> points = new ArrayList();

    @Override
    public void settings() {
        size(600, 487);
    }

    @Override
    public void setup() {
        //Set Title
        surface.setTitle("Waarnemingen van aardschokken in IJsland");

        //Set map of Iceland as background
        PImage photo = loadImage("C:/bg.png");
        background(photo);

        readText();
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{DEV018.class.getName()});
    }

    private void readText() {
        //Load JSON File
        json = loadJSONObject("C:/ijsland-metingen.json");

        //Get Array from JSON File
        JSONArray resultArray = json.getJSONArray("results");

        //Put values in appropriate ArrayLists
        for (int i = 0; i < resultArray.size(); i++) {
            JSONObject firstResults = resultArray.getJSONObject(i);
            Float depth = firstResults.getFloat("depth");
            Float size = firstResults.getFloat("size");
            Float longitude = firstResults.getFloat("longitude");
            Float latitude = firstResults.getFloat("latitude");

            Point point = new Point(longitude, latitude, size, depth);
            points.add(point);
        }
    }

    private void convert(Point point) {

        float pointA = map(point.getLongitude(), (float) -26.0, (float) -12.0, 0, 600);
        float pointB = map(point.getLatitude(), (float) 67.0, (float) 63.0, 0, 487);
        float Depth = point.getDepth();
        float Size = point.getSize();

        //Colors
        int red = color(255, 0, 0);
        int orange = color(255, 130, 0);
        int yellow = color(255, 213, 0);
        int green = color(0, 255, 0);
        int blue = color(0, 0, 255);

        //Choose the correct color
        if (Size <= -0.5 && Size >= -1) {
            fill(red);
        }

        if (Size <= 0.0 && Size >= -0.5) {
            fill(orange);
        }

        if (Size <= 0.5 && Size >= 0.0) {
            fill(yellow);
        }

        if (Size <= 1.0 && Size >= 0.5) {
            fill(green);
        } else if (Size > 1.0) {
            fill(blue);
        }

        //Create point on map with x and y
        ellipse(pointA, pointB, 8, 8);
        fill(0, 0, 0);

        //Depth between 1 and 3 is a blue triangle
        if (Depth >= 1 && Depth <= 3) {
            fill(blue);
            triangle(pointA + 5, pointB + 5, pointA + 12, pointB + 5, pointA + 7, pointB + 12);
        }

        //Depth between 4 and 6 is a green triangle
        if (Depth >= 4 && Depth <= 6) {
            fill(green);
            triangle(pointA + 5, pointB + 5, pointA + 12, pointB + 5, pointA + 7, pointB + 12);
        }

        //Depth between 7 and 9 is a yellow triangle
        if (Depth >= 7 && Depth <= 9) {
            fill(yellow);
            triangle(pointA + 5, pointB + 5, pointA + 12, pointB + 5, pointA + 7, pointB + 12);
        }

        //Depth between 10 and 12 is a orange triangle
        if (Depth >= 10 && Depth <= 12) {
            fill(orange);
            triangle(pointA + 5, pointB + 5, pointA + 12, pointB + 5, pointA + 7, pointB + 12);
        }

        //Depth higher than 12 is a red triangle
        if (Depth > 12) {
            fill(red);
            triangle(pointA + 5, pointB + 5, pointA + 12, pointB + 5, pointA + 7, pointB + 12);
        }
    }

    private void createPoints() {
        //For-loop to get items from ArrayList and then convert them to fit in window.
        for (int i = 0; i < points.size(); i++) {
            convert(points.get(i));
        }
    }

    @Override
    public void draw() {

        //Lines for latitude and longitude
        fill(255, 255, 255);
        line(65, 0, 65, 487);      // -24 long
        line(158, 0, 158, 487);    // -22 long
        line(250, 0, 250, 487);    // -20 long
        line(336, 0, 336, 487);    // -18 long
        line(427, 0, 427, 342);    // - 16 long
        line(523, 0, 523, 342);    // - 14 long
        line(0, 150, 600, 150);    // 66 lat
        line(0, 366, 412, 366);    // 64 lat

        //Legenda
        fill(255, 255, 255, 15);
        rect(412, 342, 300, 200);
        textSize(11);

        //RED
        fill(255, 0, 0);
        ellipse(424, 420, 12, 12);
        triangle(418, 345, 418, 355, 427, 345);
        fill(0, 0, 0);
        text("Diepte tussen 1.0 en 3.0", 435, 353);
        text("Omvang tussen -1.0 en -0.5", 435, 423);

        //ORANGE
        fill(255, 130, 0);
        ellipse(424, 435, 12, 12);
        triangle(418, 358, 418, 368, 427, 358);
        fill(0, 0, 0);
        text("Diepte tussen 4.0 en 6.0", 435, 366);
        text("Omvang tussen -0.5 en 0.0", 435, 438);

        //YELLOW
        fill(255, 213, 0);
        ellipse(424, 450, 12, 12);
        triangle(418, 371, 418, 381, 427, 371);
        fill(0, 0, 0);
        text("Diepte tussen 7.0 en 9.0", 435, 379);
        text("Omvang tussen 0.0 en 0.5", 435, 453);

        //GREEN
        fill(0, 255, 0);
        ellipse(424, 465, 12, 12);
        triangle(418, 384, 418, 394, 427, 384);
        fill(0, 0, 0);
        text("Diepte tussen 10.0 en 12.0", 435, 392);
        text("Omvang tussen 0.5 en 1.0", 435, 468);

        //BLUE
        fill(0, 0, 255);
        ellipse(424, 480, 12, 12);
        triangle(418, 397, 418, 407, 427, 397);
        fill(0, 0, 0);
        text("Diepte groter dan 12.0", 435, 407);
        text("Omvang groter dan 1.0", 435, 483);

        //Divider
        fill(0, 0, 0);
        line(412, 411, 600, 411);
    }

}
