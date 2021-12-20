package br.com.spring.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Movie {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
    private String title;
    private String studio;

    @ManyToMany
    private List<Producer> producer;

    public Movie()
    {
        
    }

    public Movie(String title, int year, String studio)
    {
        this.year = year;
        this.title = title;
        this.studio = studio;

        this.producer = new ArrayList<Producer>();
    }

    public void addProducer(String[] producer)
    {
        for (String producerName : producer) {
            this.producer.add(new Producer(producerName));
        }
    }

    public void addProducer(Producer producer)
    {
        this.producer.add(producer);
    }
}
