package com.charles2f.springboot_docker_cloud.services;


import com.charles2f.springboot_docker_cloud.models.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Para trabalhar com os Logs
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service  // Indica que essa classe é um serviço gerenciado pelo Spring

public class MovieService {

    // Iniciando o Log.
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    
    // Busca a URL da API
    @Value("${omdb.api.url}")
    private String apiUrl;

    // Busca a Chave da API
    @Value("${omdb.api.key}")
    private String apiKey;

    public Movie getMovieDetails(String name) {

        //Gerando o Log.
        logger.info("Buscando detalhes do filme: {}", name);


        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?apikey=" + apiKey + "&t=" + name; // Monta a URL correta

        // Loga a URL
        logger.info("URL chamada: {}", url);

        //return restTemplate.getForObject(url, Movie.class);

        try {
            Movie movie = restTemplate.getForObject(url, Movie.class);
            if (movie == null) {
                logger.warn("Nenhum filme encontrado para a URL: {}", url);
            } else {
                logger.info("Filme encontrado: {}", movie);
            }
            return movie;
        } catch (Exception e) {
            logger.error("Erro ao chamar a API OMDB para o filme: {}", name, e);
            return null;
        }

    }
}
