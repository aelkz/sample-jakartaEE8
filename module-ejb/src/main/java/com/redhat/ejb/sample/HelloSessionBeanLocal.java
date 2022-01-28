package com.redhat.ejb.sample;

import javax.ejb.Local;

@Local
public interface HelloSessionBeanLocal {
    String getLocalDateTime();
}
