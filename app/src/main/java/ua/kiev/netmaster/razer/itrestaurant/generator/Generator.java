package ua.kiev.netmaster.razer.itrestaurant.generator;

import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import ua.kiev.netmaster.razer.itrestaurant.R;
import ua.kiev.netmaster.razer.itrestaurant.communicator.IRestaurantService;
import ua.kiev.netmaster.razer.itrestaurant.activities.MyApplication;
import ua.kiev.netmaster.razer.itrestaurant.entities.MenuItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Order;
import ua.kiev.netmaster.razer.itrestaurant.entities.OrderItem;
import ua.kiev.netmaster.razer.itrestaurant.entities.Request;
import ua.kiev.netmaster.razer.itrestaurant.entities.Table;
import ua.kiev.netmaster.razer.itrestaurant.enums.RequestType;
import ua.kiev.netmaster.razer.itrestaurant.enums.Seat;
import ua.kiev.netmaster.razer.itrestaurant.enums.TableStatus;
import ua.kiev.netmaster.razer.itrestaurant.loger.L;

/**
 * Created by RAZER on 21-Apr-16.
 */
public class Generator {

    private IRestaurantService iRestaurantService;
    private MyApplication myApplication;
    private int number=0;

    public Generator(MyApplication myApplication) {
        this.iRestaurantService = myApplication;
        this.myApplication = myApplication;
    }

    public void generate(){
        generateMenu();
        makeDummyTableList(10);
        makeDummyRequestList(50);
    }

    private void makeDummyTableList(int quantity){
        L.l("makeDummyTableList()", this);
        List<Table> dummyTableList = new ArrayList<>();
        for(int i=0;i<quantity; i++){
            dummyTableList.add(makedummyTable());
        }
        iRestaurantService.setDummyTableList(dummyTableList);
    }


    private Table makedummyTable(){
        L.l("makedummyTable()", this);
        Table table = new Table();
        table.setNumber((long) number++);
        table.setSeats(Seat.values());
        table.setStatus(TableStatus.SLEEPING);
        table.setWallpaper(ContextCompat.getDrawable(myApplication, R.drawable.ic_table_plan));
        return table;
    }



    private void makeDummyRequestList(int quantity){
        L.l("makeDummyRequestList()", this);
        ArrayList<Request>requestList = new ArrayList<>();
        for(int i =0; i<quantity*.8; i++){
            requestList.add(makeDummyRequest());
        }
        for (int i = 0; i<quantity/10; i++) requestList.add(makeKitchenRequest());
        for(int i=0; i<random(3,quantity/4); i++) requestList.add(makeGetCashRequest());
        Collections.sort(requestList);
        myApplication.setRequestList(requestList);
    }

    private void generateMenu(){
        L.l("generateMenu()", this);
        if(iRestaurantService.getMenuItemList()==null){
            List<MenuItem> menuItems = new ArrayList<>();
            int counter = 0;
            String[] dishesNames = myApplication.getResources().getStringArray(R.array.dishes);
            for(String name : dishesNames){
                MenuItem menuItem = new MenuItem();
                menuItem.setName(name);
                menuItem.setDescription("Very tasty " + name);
                menuItem.setPrice(dishesPrices[counter]);
                menuItem.setIcon(ContextCompat.getDrawable(myApplication, R.drawable.ic_salad_icon));
                menuItems.add(menuItem);
                counter++;
            }
            iRestaurantService.setMenuItemList(menuItems);
        }
    }

    private Request makeDummyRequest(){
        L.l("makeDummyRequest()", this);
        Request request = makeNewReq();
        Queue<RequestType> reqTypes =new LinkedList<>();
        //make 1-4 tempReqest from visitor.
        List<Integer> uniqRandList = getUniqRandom();
        for(int i = 0; i<uniqRandList.size(); i++) reqTypes.add(RequestType.values()[uniqRandList.get(i)]);
        // if necessary set default Order into tempReqest
        for(RequestType requestType: reqTypes){
            if(requestType==RequestType.GetCash || requestType==RequestType.Cash){
                request.setOrder(makeNewOrder());
                break;
            }
        }
        request.setRequestTypes(reqTypes);
        request.setTime(new Date());
        return request;
    }

    private Request makeNewReq(){
        Request request = new Request();
        request.setSeat(Seat.values()[random(0, Seat.values().length)]);
        request.setTable(iRestaurantService.getDummyTableList().get(random(0, iRestaurantService.getDummyTableList().size() - 1)));
        request.setTime(new Date());
        return request;
    }

    private Order makeNewOrder(){
        Order order = new Order();
        order.setTotal(random(20, 500));
        OrderItem orderIte = new OrderItem();
        orderIte.setMenuItem(iRestaurantService.getMenuItemList().get(random(0, iRestaurantService.getMenuItemList().size())));
        order.setItems(new OrderItem[]{orderIte});
        return order;
    }

    private Request makeKitchenRequest(){
        Request request = makeNewReq();
        Queue<RequestType> reqTypes =new LinkedList<>();
        reqTypes.add(RequestType.Kitchen);
        request.setRequestTypes(reqTypes);
        request.setOrder(makeNewOrder());
        return request;
    }

    private Request makeGetCashRequest(){
        Request request = makeNewReq();
        Queue<RequestType> reqTypes =new LinkedList<>();
        reqTypes.add(RequestType.GetCash);
        request.setRequestTypes(reqTypes);
        request.setOrder(makeNewOrder());
        return request;
    }

    public int random(int minimum, int maximum){
        L.l("random()", this);
        Random r = new Random();
        int i1 = r.nextInt(maximum - minimum) + minimum;
        L.l("random return  = " +i1);
        return i1;
    }

    private List<Integer> getUniqRandom(){
        L.l("getUniqRandom()", this);
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> resp = new ArrayList<>();
        for (int i=0; i<5; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<random(1,5); i++) {
            //System.out.println(list.get(i));
            resp.add(list.get(i));
        }
        L.l("return = "+resp.toString());
        L.l("resp.size = "+resp.size());
        return resp;
    }

    private Double[] dishesPrices = {25.50d, 30.00d, 20.45d, 82.45d, 55.56d, 67.40d, 30.10d, 21.50d, 21.50d, 53.70d, 25.00d};




}
