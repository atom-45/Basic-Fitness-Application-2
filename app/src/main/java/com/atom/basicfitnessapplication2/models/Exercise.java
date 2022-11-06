package com.atom.basicfitnessapplication2.models;

import androidx.annotation.NonNull;

import com.google.firebase.database.PropertyName;

import java.util.Objects;

public class Exercise {

    private double temperature;
    private double acceleration_x;
    private double acceleration_y;
    private double getAcceleration_z;
    private double gyro_x;
    private double gyro_y;
    private double gyro_z;
    private double distance;
    private String date;
    private String start_time;
    private String delay_time;

    public Exercise(){}
    public Exercise(double temperature, double acceleration_x, double acceleration_y,
                    double getAcceleration_z, double gyro_x, double gyro_y, double gyro_z,
                    double distance, String date, String start_time, String delay_time) {

        this.temperature = temperature;
        this.acceleration_x = acceleration_x;
        this.acceleration_y = acceleration_y;
        this.getAcceleration_z = getAcceleration_z;
        this.gyro_x = gyro_x;
        this.gyro_y = gyro_y;
        this.gyro_z = gyro_z;
        this.distance = distance;
        this.date = date;
        this.start_time = start_time;
        this.delay_time = delay_time;
    }

    @PropertyName("temperature")
    public double getTemperature() {
        return temperature;
    }

    @PropertyName("acceleration_x")
    public double getAcceleration_x() {
        return acceleration_x;
    }

    @PropertyName("acceleration_y")
    public double getAcceleration_y() {
        return acceleration_y;
    }

    @PropertyName("acceleration_z")
    public double getAcceleration_z() {
        return getAcceleration_z;
    }

    @PropertyName("gyro_x")
    public double getGyro_x() {
        return gyro_x;
    }

    @PropertyName("gyro_y")
    public double getGyro_y() {
        return gyro_y;
    }

    @PropertyName("gyro_z")
    public double getGyro_z() {
        return gyro_z;
    }

    @PropertyName("distance")
    public double getDistance() {
        return distance;
    }

    @PropertyName("date")
    public String getDate() {
        return date;
    }

    public String getStart_time() {
        return start_time;
    }

    @PropertyName("delay_time")
    public String getDelay_time() {
        return delay_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exercise)) return false;
        Exercise exercise = (Exercise) o;
        return Double.compare(exercise.getTemperature(), getTemperature()) == 0 &&
                Double.compare(exercise.getAcceleration_x(), getAcceleration_x()) == 0 &&
                Double.compare(exercise.getAcceleration_y(), getAcceleration_y()) == 0 &&
                Double.compare(exercise.getAcceleration_z(), getAcceleration_z()) == 0 &&
                Double.compare(exercise.getGyro_x(), getGyro_x()) == 0 &&
                Double.compare(exercise.getGyro_y(), getGyro_y()) == 0 &&
                Double.compare(exercise.getGyro_z(), getGyro_z()) == 0 &&
                Double.compare(exercise.getDistance(), getDistance()) == 0 &&
                getDate().equals(exercise.getDate()) &&
                getStart_time().equals(exercise.getStart_time()) &&
                getDelay_time().equals(exercise.getDelay_time());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemperature(), getAcceleration_x(), getAcceleration_y(),
                getAcceleration_z(), getGyro_x(), getGyro_y(), getGyro_z(),
                getDistance(), getDate(), getStart_time(), getDelay_time());
    }

    @NonNull
    @Override
    public String toString() {
        return "Exercise{" +
                "temperature='" + temperature + '\'' +
                ", acceleration_x='" + acceleration_x + '\'' +
                ", acceleration_y='" + acceleration_y + '\'' +
                ", getAcceleration_z='" + getAcceleration_z + '\'' +
                ", gyro_x='" + gyro_x + '\'' +
                ", gyro_y='" + gyro_y + '\'' +
                ", gyro_z='" + gyro_z + '\'' +
                ", distance='" + distance + '\'' +
                ", date='" + date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", delay_time='" + delay_time + '\'' +
                '}';
    }
}
