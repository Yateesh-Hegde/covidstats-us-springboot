package com.example.covidstats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.covidstats.entity.CovidStat;
import com.example.covidstats.service.CovidStatsService;

/**
 *
 * @author yateeshhegde
 *
 * Rest controller for fetching covoid stats for USA,world
 * pagination supported for US data
 * 
 */

@RestController
public class CovidStatsController {
	@Autowired
	CovidStatsService covidStatsService;

	@GetMapping("/world")
	public List<CovidStat> getAll() throws Exception {

		return covidStatsService.getStats(null);

	}

	@GetMapping("/usa")
	public List<CovidStat> getUsa() throws Exception {

		return covidStatsService.getStats("US");

	}

	@GetMapping("/usa/page")
	public Page<CovidStat> getByPageUsa(@RequestParam(required = true, name = "page_no") Integer pageNo,
			@RequestParam(required = true, name = "item_count") Integer itemCount,
			@RequestParam(required = false, name = "sort_by") String sortBy) throws Exception {

		return covidStatsService.findPaginated(pageNo, itemCount, sortBy);

	}

}
