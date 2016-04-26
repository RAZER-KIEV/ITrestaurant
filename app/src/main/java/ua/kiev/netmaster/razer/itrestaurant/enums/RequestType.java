package ua.kiev.netmaster.razer.itrestaurant.enums;

/**
 * Created by RAZER on 07-Apr-16.
 */
public enum RequestType {
    ComeToMe(0), Taxi(0), CreditCard(0), Cash(0), Cutlery(0), Kitchen(1), GetCash(2),;

    private int priority;

    RequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
