package com.bootcamp.helpers;

import com.bootcamp.enums.UniteDeTemp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EntryInfos {

    private String entityType;
    private String startDate;
    private String endDate;
    private T T;

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public com.bootcamp.helpers.T getT() {
        return T;
    }

    public void setT(com.bootcamp.helpers.T t) {
        T = t;
    }
}

