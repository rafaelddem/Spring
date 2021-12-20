package br.com.spring.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.spring.main.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>
{

    Producer findByName(String name);

    // @Query("select id from producer where id = :id", nativeQuery = true)
    // List<Producer> findAllId(@Param("id") int id);

    // @Query("update producer set maxInterval = :maxInterval, yearUntilSecondAward = :yearUntilSecondAward, upToDate = :upToDate where id = :id");
    // Producer updateProducer(@Param("id") int id, @Param("maxInterval") int maxInterval, @Param("yearUntilSecondAward") int yearUntilSecondAward, @Param("upToDate") boolean upToDate);

}
