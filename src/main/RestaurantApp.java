package main;

import facade.RestaurantFacade;

public class RestaurantApp{
    public static void main(String[] args){
        RestaurantFacade facade = new RestaurantFacade();
        facade.start();
    }
}
