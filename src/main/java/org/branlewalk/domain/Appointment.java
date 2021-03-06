package org.branlewalk.domain;

import java.util.Date;

public interface Appointment {

    int getMonthValue();

    String getType();

    String getDescription();

    String getCustomerName();

    String getUserName();

    String getTitle();

    String getLocation();

    String getUrl();

    Date getStart();

    Date getEnd();

    int getCustomerId();

    int getUserId();

    String getContact();

    int getId();

    String getStartDateString();

    String getEndDateString();
}
