package com.ironyard.repo;

import com.ironyard.data.Movie;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jasonskipper on 10/31/16.
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{

}
