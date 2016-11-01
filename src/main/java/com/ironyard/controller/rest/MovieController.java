package com.ironyard.controller.rest;

import com.ironyard.data.Movie;
import com.ironyard.repo.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jasonskipper on 10/31/16.
 */
@RestController
@RequestMapping(path = "/rest/movie")
public class MovieController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MovieRepository movieRepository;


    /**
     * Save the provided movie
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Movie save(@RequestBody Movie aMovie) {
        movieRepository.save(aMovie);
        return movieRepository.findOne(aMovie.getId());
    }

    /**
     * Update the movie
     * @param aMovie
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public Movie update(@RequestBody Movie aMovie) {
        movieRepository.save(aMovie);
        return movieRepository.findOne(aMovie.getId());
    }

    /**
     * Get the Movie by id and return it
     * @param id
     * @return
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Movie show(@PathVariable Long id) {
        return movieRepository.findOne(id);
    }


    /**
     * List the movies matching the request
     * @param page
     * @param size
     * @param sortby
     * @param direction
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Iterable<Movie> listAll(@RequestParam(value = "page") Integer page,
                                    @RequestParam("size") Integer size,
                                    @RequestParam(value = "sortby", required = false) String sortby,
                                    @RequestParam(value = "dir", required = false) Sort.Direction direction) {

        // DEFAULT Sort property
        if (sortby == null) {
            sortby = "title";
        }

        // DEFAULT Sort direction
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        Sort s = new Sort(direction, sortby);
        PageRequest pr = new PageRequest(page, size, s);
        return movieRepository.findAll(pr);
    }

    /**
     * Delete the specified movie
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public Movie delete(@PathVariable Long id) {
        Movie deleted = movieRepository.findOne(id);
        movieRepository.delete(id);
        return deleted;
    }

    /**
     * Catch any errors from this contorller
     * @param e
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public String nfeHandler(Throwable e) {
        return "Something went wrong :/";
    }
}


