package br.com.spring.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.main.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer>
{

    Movie findByTitle(String title);
    
}
