/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class Point {
    private float Longitude;
    private float Latitude;
    private float Size;
    private float Depth;

    public Point(float Longitude, float Latitude, float Size, float Depth) {
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.Size = Size;
        this.Depth = Depth;
    }

    public float getLongitude() {
        return Longitude;
    }

    private void setLongitude(float Longitude) {
        this.Longitude = Longitude;
    }

    public float getLatitude() {
        return Latitude;
    }

    private void setLatitude(float Latitude) {
        this.Latitude = Latitude;
    }

    public float getSize() {
        return Size;
    }

    private void setSize(float Size) {
        this.Size = Size;
    }

    public float getDepth() {
        return Depth;
    }

    private void setDepth(float Depth) {
        this.Depth = Depth;
    }
    
    
}
