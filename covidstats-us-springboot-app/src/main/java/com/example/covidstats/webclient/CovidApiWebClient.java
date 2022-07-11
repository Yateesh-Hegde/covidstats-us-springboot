package com.example.covidstats.webclient;

import java.time.Duration;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import com.example.covidstats.model.ApiResponseEntity;

import io.netty.handler.timeout.TimeoutException;
import reactor.util.retry.Retry;

/**
 * 
 * @author yateeshhegde
 *
 *         this is the webclient which make communication to covid api and gets
 *         the result will retry for 5 times in case of timeout or http server
 *         exceptions
 */
@Component
public class CovidApiWebClient {

	@Autowired
	private Builder statsCountWebClientBuilder;

	@Value("${covidapi.baseURL}")
	String baseURL;

	@Value("${covidapi.rapid_api_new_key}")
	String rapidApiKey;

	@Value("${covidapi.rapid_api_host}")
	String rapidApiHost;

	public ApiResponseEntity getStatsFromApi(String countryCode) throws Exception {

		WebClient webClient = statsCountWebClientBuilder.defaultHeaders(httpHeaders())
				.baseUrl(baseURL.concat(countryCode != null ? "?country=" + countryCode : "")).build();

		ApiResponseEntity result = webClient.get()
				.exchangeToMono(response -> response.bodyToMono(ApiResponseEntity.class))
				.retryWhen(Retry.backoff(5, Duration.ofSeconds(8))
						.filter(throwable -> throwable instanceof HttpServerErrorException
								|| throwable instanceof WebClientRequestException
										&& throwable.getCause() instanceof TimeoutException))
				.block();

		return result;

	}

	/**
	 * 
	 * @return headers setting
	 */
	private Consumer<HttpHeaders> httpHeaders() {
		return headers -> {
			headers.add("X-RapidAPI-Key", rapidApiKey);
			headers.add("X-RapidAPI-Host", rapidApiHost);
		};
	}

}
