package tasks;

import model.Movie;
import viewmodel.Histogram;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class HistogramBuilder {
    private final Stream<Movie> movies;
    private final Map<String, String> labels;

    private HistogramBuilder(Stream<Movie> movies) {
        this.movies = movies;
        this.labels = new HashMap<>();
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram(labels);
        movies.map(binarize).forEach(histogram::put);
        return histogram;
    }

    public static HistogramBuilder with(Stream<Movie> movies) {
        if(movies == null) throw new IllegalArgumentException("movies cannot be null");
        return new HistogramBuilder(movies);
    }

    public HistogramBuilder title(String label){
        labels.put("label",label);
        return this;
    }

    public HistogramBuilder x(String label){
        labels.put("x",label);
        return this;
    }

    public HistogramBuilder y(String label){
        labels.put("y",label);
        return this;
    }

    public HistogramBuilder legend(String label){
        labels.put("legend",label);
        return this;
    }

}


