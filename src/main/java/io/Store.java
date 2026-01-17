package io;

import model.Movie;

import java.util.List;
import java.util.stream.Stream;

public interface Store {
    public Stream<Movie> movies();
}


