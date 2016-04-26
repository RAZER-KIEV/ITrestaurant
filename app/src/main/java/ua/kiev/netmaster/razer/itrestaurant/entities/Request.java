package ua.kiev.netmaster.razer.itrestaurant.entities;

import java.util.Date;
import java.util.List;
import java.util.Queue;

import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class Request implements Comparable<Request>{
    private Date time;
    private Table table;
    private Seat seat;
    private Order order;
    private Queue<RequestType> requestTypes;

    public Request() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public Queue<RequestType> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(Queue<RequestType> requestTypes) {
        this.requestTypes = requestTypes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Request{" +
                "time=" + time +
                ", table=" + table +
                ", seat=" + seat +
                ", order=" + order +
                ", requestTypes=" + requestTypes +
                '}';
    }

    @Override
    public int compareTo(Request another) {
        //Request anoth = (Request) another;
        if(this.equals(another)) return 0;
        if(getRequestTypes().element().getPriority() < another.getRequestTypes().element().getPriority()) return 1;
        return -1;
    }
}
