package com.example.covidstats.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CovidStatsData {
	
	public String getLastChecked() {
		return lastChecked;
	}

	public void setLastChecked(String lastChecked) {
		this.lastChecked = lastChecked;
	}

	public List<CovidStatsRow> getCovid19Stats() {
		return covid19Stats;
	}

	public void setCovid19Stats(List<CovidStatsRow> covid19Stats) {
		this.covid19Stats = covid19Stats;
	}

	@JsonProperty
	String lastChecked;
	
	@JsonProperty
	List<CovidStatsRow> covid19Stats;

}
