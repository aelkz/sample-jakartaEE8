package com.redhat.war.sample.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import com.redhat.war.sample.model.Member;
import com.redhat.war.sample.service.MemberRegistration;

/**
 * Registers a new Member
 * <p>
 * <p>
 * The @Model stereotype is a convenience mechanism to make this a
 * request-scoped bean that has an EL name
 */
@Model
public class MemberController {

    @Inject
    private FacesContext facesContext;

    @Inject
    private MemberRegistration memberRegistration;

    @EJB
    private com.redhat.ejb.sample.HelloSessionBeanRemote helloSessionBeanRemote;

    private Member newMember;

    @Produces
    @Named
    public Member getNewMember() {
        return newMember;
    }

    public void register() {
        try {
            memberRegistration.register(newMember);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful"));
            initNewMember();

            // try EJB call (from module-ejb)
            try {
                System.out.println("EJB call - user created at " + helloSessionBeanRemote.getLocalDateTime());
            }catch (Exception e) {
                System.out.println("EJB call error");
            }
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    errorMessage, "Registration unsuccessful"));
        }
    }

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

}
