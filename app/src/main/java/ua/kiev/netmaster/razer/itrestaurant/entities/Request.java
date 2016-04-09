package ua.kiev.netmaster.razer.itrestaurant.entities;

import java.util.Date;
import java.util.List;

import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class Request {
    private Date time;
    private Table table;
    private Seat seat;
    private List<RequestType> requestTypes;

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

    public List<RequestType> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(List<RequestType> requestTypes) {
        this.requestTypes = requestTypes;
    }

    @Override
    public String toString() {
        return "Request{" +
                "time=" + time +
                ", table=" + table +
                ", seat=" + seat +
                ", requestTypes=" + requestTypes +
                '}';
    }
}
