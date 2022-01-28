package com.redhat.ejb.sample;

import javax.ejb.Remote;

@Remote
public interface HelloSessionBeanRemote {
    String getLocalDateTime();
}
