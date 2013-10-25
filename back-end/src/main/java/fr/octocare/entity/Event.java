package fr.octocare.entity;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Event {

    @Id
    private String id;

    @Parent
    private Ref<Octo> octo;

    private Ref<Hexa> author;

    private Date date;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ref<Octo> getOcto() {
        return octo;
    }

    public void setOcto(Ref<Octo> octo) {
        this.octo = octo;
    }

    public Ref<Hexa> getAuthor() {
        return author;
    }

    public void setAuthor(Ref<Hexa> author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
