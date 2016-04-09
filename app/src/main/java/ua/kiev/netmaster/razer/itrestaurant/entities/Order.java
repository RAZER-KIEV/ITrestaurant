package ua.kiev.netmaster.razer.itrestaurant.entities;

import java.util.Arrays;

import ua.kiev.netmaster.razer.itrestaurant.enums.OrderStatus;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;
import ua.kiev.netmaster.razer.itrestaurant.enums.TableStatus;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class Order {
    private Table table;
    private Seat seat;
    private double total;
    private OrderStatus status;
    private OrderItem[] items;
    private Order[] orders;
    private String messages;

    public Order() {
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderItem[] getItems() {
        return items;
    }

    public void setItems(OrderItem[] items) {
        this.items = items;
    }

    public Order[] getOrders() {
        return orders;
    }

    public void setOrders(Order[] orders) {
        this.orders = orders;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Order{" +
                "table=" + table +
                ", seat=" + seat +
                ", total=" + total +
                ", status=" + status +
                ", items=" + Arrays.toString(items) +
                ", orders=" + Arrays.toString(orders) +
                ", messages='" + messages + '\'' +
                '}';
    }
}
