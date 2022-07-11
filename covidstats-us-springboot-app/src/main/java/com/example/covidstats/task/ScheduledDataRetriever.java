package com.example.covidstats.task;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.covidstats.entity.CovidStat;
import com.example.covidstats.model.ApiResponseEntity;
import com.example.covidstats.repo.CovidStatsRepository;
import com.example.covidstats.webclient.CovidApiWebClient;

/**
 * 
 * @author yateeshhegde
 *This is scheduled job which fetches the data from covid-api priodically an stores in db
 *
 *
 */
@Component
public class ScheduledDataRetriever {
	private static final Logger logger = LoggerFactory.getLogger(ScheduledDataRetriever.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private CovidStatsRepository repository;

	@Autowired
	private CovidApiWebClient covidApiWebClient;

	
	
	@Transactional
	@Scheduled(fixedRateString = "${covidapi.data_refresh_interval: 500}", initialDelay = 1)
	public void fetchDataFromCovidApiAndUpdateDBForAll() throws Exception {
		logger.info("Started Fetching data from api ALL:: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));

		ApiResponseEntity result = covidApiWebClient.getStatsFromApi(null);

		if (result.getError() == false) {

			logger.info("Started Storing data in db ALL :: Execution Time - {}",
					dateTimeFormatter.format(LocalDateTime.now()));

			repository.deleteByUpdateTimeEquals(new Date(System.currentTimeMillis()));

			List<CovidStat> list = result.getData().getCovid19Stats().stream()
					.map(covidStat -> new CovidStat(covidStat)).collect(Collectors.toList());

			repository.saveAll(list);

		}

	}

}
