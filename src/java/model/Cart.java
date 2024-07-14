/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoa
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        items=new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public int getQuantityById(String id){
        return getItemById(id).getQuantity();
    }
    private Item getItemById(String id){
        for (Item i : items) {
            if(i.getProduct().getId().equalsIgnoreCase(id))
                return i;
        }
        return null;
    }
    public void addItem(Item t){
        if(getItemById(t.getProduct().getId())!=null){
            Item m =getItemById(t.getProduct().getId());
            m.setQuantity(m.getQuantity()+t.getQuantity());
        }else{
            items.add(t);
        }
    }
    public void removeItem(String id){
        if(getItemById(id)!=null){
            items.remove(getItemById(id));
        }
    }
    public double getTotalMoney(){
        double t=0.0;
        for (Item i : items) {
            t=t+(i.getQuantity()*i.getProduct().getPrice()*1.2);
        }
        return t;
    }
}
