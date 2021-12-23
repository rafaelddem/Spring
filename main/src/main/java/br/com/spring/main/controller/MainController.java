package br.com.spring.main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.main.model.Movie;
import br.com.spring.main.model.Producer;
import br.com.spring.main.repository.MovieRepository;
import br.com.spring.main.repository.ProducerRepository;

@RestController
public class MainController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @RequestMapping("/")
    // @ResponseBody
    public String hello() 
    {
        String retorno = "";
        // String arquivoCSV = "C:\\Users\\Rafael\\Documents\\Git\\Spring\\main\\files\\movielist.csv";
        // BufferedReader br = null;
        // String linha = "";
        // String csvDivisor = ";";

        // try {
        //     br = new BufferedReader(new FileReader(arquivoCSV));
        //     while ((linha = br.readLine()) != null) {
        //         String[] pais = linha.split(csvDivisor);
        //         for (String celula : pais) {
        //             retorno += celula + " |";
        //         }
        //         retorno += "<br>";
        //     }
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        return retorno;
    }

    @RequestMapping("/loadData")
    // @ResponseBody
    public String loadData()
    {
        String retorno = "";
        BufferedReader br = null;
        String line = "";
        String csvDivisor = ";";

        try {
            File path = new File("files");
            File[] files = path.listFiles();

            for (File file : files) {
                br = new BufferedReader(new FileReader(file));
                br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] lineData = line.split(csvDivisor);

                    // movie
                    Movie movie = movieRepository.findByTitle(lineData[1]);

                    if (movie != null) 
                        continue;

                    movie = new Movie(lineData[1], Integer.parseInt(lineData[0]), lineData[2]);

                    //producer
                    String producerCell = lineData[3].replaceAll(" and ",",").replaceAll(", ",",");
                    String[] producers = producerCell.split(",");
                    for (String producerName : producers) {
                        if (producerName.isBlank()) 
                            continue;

                        Producer producer = producerRepository.findByName(producerName);

                        if (producer == null) {
                            producer = new Producer(producerName);
                            producerRepository.save(producer);
                        }

                        movie.addProducer(producer);
                    }

                    movieRepository.save(movie);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @RequestMapping("/statistics")
    @ResponseBody
	@Transactional
    public String statistics()
    {
        Collection<Producer> producers = producerRepository.findAll();
        Collection<Movie> movies = movieRepository.findAll();

        int maxIntervalAbsolute = 0;
        int minIntervalAbsolute = 999;

        for (Producer producer : producers) {
            Collection<Movie> moviesProducer = movies.stream().filter(deal -> deal.getProducers().contains(producer)).sorted(Comparator.comparing(Movie::getYear).reversed()).toList();
            Iterator<Movie> it = moviesProducer.iterator();
            int year = it.next().getYear();
            while (it.hasNext()) {
                int nextYear = it.next().getYear();
                int interval = year - nextYear;

                if (interval > producer.getMaxInterval()) {
                    producer.setMaxInterval(interval);

                    int[] yearsMax = {nextYear, year};
                    producer.setYearForMaxInterval(yearsMax);

                    if (interval > maxIntervalAbsolute) 
                        maxIntervalAbsolute = interval;
                }

                if (interval < producer.getMinInterval()) {
                    producer.setMinInterval(interval);

                    int[] yearsMin = {nextYear, year};
                    producer.setYearForMinInterval(yearsMin);

                    if (interval < minIntervalAbsolute) 
                        minIntervalAbsolute = interval;
                }

                year = nextYear;
            }
        }

        int minIntervalAbsoluteFilter = minIntervalAbsolute;
        Collection<Producer> producersMinInterval = producers.stream().filter(deal -> deal.getMinInterval() == minIntervalAbsoluteFilter).toList();
        
        int maxIntervalAbsoluteFilter = maxIntervalAbsolute;
        Collection<Producer> producersMaxInterval = producers.stream().filter(deal -> deal.getMaxInterval() == maxIntervalAbsoluteFilter).toList();

        String responseMin = "\"min\": [";
        for (Producer producer : producersMinInterval) {
            responseMin += "{";
            responseMin += "\"producer\": \"" + producer.getName() + "\", ";
            responseMin += "\"interval\": " + producer.getMinInterval() + ", ";
            responseMin += "\"previousWin\": " + producer.getYearForMinInterval()[0] + ", ";
            responseMin += "\"followingWin\": " + producer.getYearForMinInterval()[1] + ", ";
            responseMin += "},";
        }
        responseMin += "]";

        String responseMax = "\"max\": [";
        for (Producer producer : producersMaxInterval) {
            responseMax += "{";
            responseMax += "\"producer\": \"" + producer.getName() + "\", ";
            responseMax += "\"interval\": " + producer.getMaxInterval() + ", ";
            responseMax += "\"previousWin\": " + producer.getYearForMaxInterval()[0] + ", ";
            responseMax += "\"followingWin\": " + producer.getYearForMaxInterval()[1] + ", ";
            responseMax += "},";
        }
        responseMax += "]";
        // Collection<Producer> producerMaxInterval = new ArrayList<Producer>();
        // producerMaxInterval.add(producers.stream().sorted(Comparator.comparing(Producer::getMaxInterval).reversed()).toList().get(0));

        // producers.stream().max(Comparator.comparing(Producer::getMaxInterval));
        // return producerMaxInterval;

        return "{" + responseMin + ", " + responseMax + "}";
    }
}
