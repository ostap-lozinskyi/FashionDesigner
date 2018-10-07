package ua.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "emailCredentials")
public class EmailCredentials extends AbstractEntity {

    public EmailCredentials() {
    }

    private String senderMail;

    private String senderPassword;

    private String receiverMail;

    public String getSenderMail() {
        return senderMail;
    }

    public void setSenderMail(String senderMail) {
        this.senderMail = senderMail;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    public String getReceiverMail() {
        return receiverMail;
    }

    public void setReceiverMail(String receiveMail) {
        this.receiverMail = receiveMail;
    }
}