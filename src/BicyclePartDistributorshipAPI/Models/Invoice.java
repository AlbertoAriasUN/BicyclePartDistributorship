/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BicyclePartDistributorshipAPI.Models;

import Database.IDatabaseModel;
import java.time.LocalDate;

/**
 *
 * @author colinvitkus
 */
public class Invoice implements IDatabaseModel{
    private String name;
    private LocalDate day;
    private int amount;
    
    public Invoice(String name, LocalDate day, int amount)
    {
        this.name = name;
        this.day = day;
        this.amount = amount; 
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the day
     */
    public LocalDate getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(LocalDate day) {
        this.day = day;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Object getPrimaryKey() {
        return null;
    }
    
}
