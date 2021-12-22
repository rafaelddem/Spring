package br.com.spring.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.main.model.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Integer>
{

    Producer findByName(String name);

}
