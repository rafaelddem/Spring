package br.com.spring.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producer {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    // private int maxInterval;
    // private int yearUntilSecondAward = 999;
    // private boolean upToDate;

    public Producer() {}

    public Producer(String name)
    {
        this.name = name;
    }

    // public int getId()
    // {
    //     return this.id;
    // }

    public String getName()
    {
        return this.name;
    }

    // public int getMaxInterval()
    // {
    //     return this.maxInterval;
    // }

    // public int getYearUntilSecondAward()
    // {
    //     return this.yearUntilSecondAward;
    // }

    // public void setUpToDate(boolean upToDate)
    // {
    //     this.upToDate = upToDate;
    // }

    // public boolean getUpToDate()
    // {
    //     return this.upToDate;
    // }

}
