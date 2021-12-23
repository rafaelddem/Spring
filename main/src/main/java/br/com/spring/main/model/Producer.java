package br.com.spring.main.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Producer {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @Transient
    private int maxInterval;

    @Transient
    private int minInterval = 999;

    @Transient
    private int[] yearForMaxInterval;

    @Transient
    private int[] yearForMinInterval;

    @Transient
    private List<Movie> movies;// = new List<Movie>();

    public Producer() {}

    public Producer(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setMaxInterval(int maxInterval)
    {
        this.maxInterval = maxInterval;
    }

    public int getMaxInterval()
    {
        return this.maxInterval;
    }

    public void setMinInterval(int minInterval)
    {
        this.minInterval = minInterval;
    }

    public int getMinInterval()
    {
        return this.minInterval;
    }

    public void setYearForMaxInterval(int[] yearForMaxInterval)
    {
        this.yearForMaxInterval = yearForMaxInterval;
    }

    public int[] getYearForMaxInterval()
    {
        return this.yearForMaxInterval;
    }

    public void setYearForMinInterval(int[] yearForMinInterval)
    {
        this.yearForMinInterval = yearForMinInterval;
    }

    public int[] getYearForMinInterval()
    {
        return this.yearForMinInterval;
    }

}
