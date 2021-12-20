package br.com.spring.main.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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
                    Movie movie = new Movie(lineData[1], Integer.parseInt(lineData[0]), lineData[2]);

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

    @RequestMapping("/statics")
    @ResponseBody
	@Transactional
    public String statics()
    {
        List<Producer> producers = producerRepository.findAll();
        for (Producer producer : producers) {
            // producer.setUpToDate(true);
            // producerRepository.updateProducer(producer.getId(), producer.getMaxInterval(), producer.getYearUntilSecondAward(), producer.getUpToDate());
        }
        return "";




        
        // Producer producer = producerRepository.findByName("asd");
        // producer.setUpToDate(true);
    }
}
