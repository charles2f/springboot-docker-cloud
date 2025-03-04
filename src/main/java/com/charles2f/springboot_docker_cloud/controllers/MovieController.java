package com.charles2f.springboot_docker_cloud.controllers;


import com.charles2f.springboot_docker_cloud.models.Movie;
import com.charles2f.springboot_docker_cloud.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Incluindo Log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller  // Indica que esta classe é um controlador
public class MovieController {

    // Chamada para o Log.
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    // Método que recebe o nome do filme e retorna a página HTML com os resultados
    @GetMapping("/movie")
    public String getMovie(@RequestParam String name, Model model) {

        // Chama o Log.
        //logger.info("Acessando o método getMovie com o nome: {}", name);  // Log de acesso

        // Chama o serviço para obter os detalhes do filme
        Movie movie = movieService.getMovieDetails(name);  

        //Log do Detalhe do Filme
        //logger.info("Detalhes do filme: {}", movie);  // Log dos detalhes do filme

        // Passa o objeto Movie para o template HTML
        // model.addAttribute("movie", movie); 
        model.addAttribute("movie", movie); 


        
        try {
            //Movie movie = movieService.getMovieDetails(name);
            if (movie == null) {
                //logger.warn("Nenhum filme encontrado para: {}", name);
                model.addAttribute("error", "Filme não encontrado.");
            } else {
                //logger.info("Filme encontrado: {}", movie);
                model.addAttribute("movie", movie);
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar o filme: {}", name, e);
            model.addAttribute("error", "Erro ao buscar o filme.");
        }
        
        //System.out.println("Detalhes do filme: " + movie); // Adicione um log para ver o objeto Movie
        return "movie";  // Retorna o nome do template 'movie.html'
    }
}
