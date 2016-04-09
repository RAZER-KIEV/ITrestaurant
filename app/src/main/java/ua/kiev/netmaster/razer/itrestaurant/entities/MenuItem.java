package ua.kiev.netmaster.razer.itrestaurant.entities;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class MenuItem {
    private String name;
    private double price;
    private String description;
    private Drawable icon;

    public MenuItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", icon=" + icon +
                '}';
    }
}
