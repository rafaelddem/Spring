package br.com.spring.main.controller.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import br.com.spring.main.model.Producer;

public class ProducerDto {

    private String name;
    private int maxInterval;
    private int minInterval = 999;
    private int[] yearForMaxInterval;
    private int[] yearForMinInterval;

    public ProducerDto(Producer producer)
    {
        this.name = producer.getName();
        this.maxInterval = producer.getMaxInterval();
        this.minInterval = producer.getMinInterval();
        this.yearForMaxInterval = producer.getYearForMaxInterval();
        this.yearForMinInterval = producer.getYearForMinInterval();
    }

	public static Collection<ProducerDto> converter(Collection<Producer> producers) {
		return producers.stream().map(ProducerDto::new).collect(Collectors.toList());
	}

}
