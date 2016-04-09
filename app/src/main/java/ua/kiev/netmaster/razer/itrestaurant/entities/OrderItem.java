package ua.kiev.netmaster.razer.itrestaurant.entities;

import ua.kiev.netmaster.razer.itrestaurant.enums.OrderItemStatus;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class OrderItem {
    private String name;
    private double price;
    private OrderItemStatus status;
    private MenuItem menuItem;

    public OrderItem() {
    }

    public String getName() {
        if(menuItem!=null) return menuItem.getName();
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        if(menuItem!=null) return menuItem.getPrice();
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OrderItemStatus getStatus() {
        return status;
    }

    public void setStatus(OrderItemStatus status) {
        this.status = status;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", menuItem=" + menuItem +
                '}';
    }
}
