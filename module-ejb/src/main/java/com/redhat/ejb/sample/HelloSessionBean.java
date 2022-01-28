package com.redhat.ejb.sample;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Stateless
@Local( HelloSessionBeanLocal.class  )
@Remote( HelloSessionBeanRemote.class )
public class HelloSessionBean implements HelloSessionBeanLocal, HelloSessionBeanRemote  {
    public String getLocalDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }
}
