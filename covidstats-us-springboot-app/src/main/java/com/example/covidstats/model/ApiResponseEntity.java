package com.example.covidstats.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponseEntity {

	public Boolean getError() {
		return error;
	}

	public void setError(Boolean error) {
		this.error = error;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CovidStatsData getData() {
		return data;
	}

	public void setData(CovidStatsData data) {
		this.data = data;
	}

	@JsonProperty
	Boolean error;

	@JsonProperty
	Integer statusCode;

	@JsonProperty
	String message;

	@JsonProperty
	CovidStatsData data;

}
