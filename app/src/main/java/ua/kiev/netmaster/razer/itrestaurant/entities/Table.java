package ua.kiev.netmaster.razer.itrestaurant.entities;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.Arrays;

import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;
import ua.kiev.netmaster.razer.itrestaurant.enums.TableStatus;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class Table {
    private Long number;
    private TableStatus status;
    private Drawable wallpaper;
    private Seat[] seats;

    public Table() {
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus status) {
        this.status = status;
    }

    public Drawable getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(Drawable wallpaper) {
        this.wallpaper = wallpaper;
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Table{" +
                "number=" + number +
                ", status=" + status +
                ", wallpaper=" + wallpaper +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }
}
