package application;

import model.Movie;
import tasks.HistogramBuilder;
import viewmodel.Histogram;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    static void main() {
        Stream<Movie> movies = new RemoteStore(Main::fromTsv).movies()
                .filter(m -> m.year() > 1900).filter(m -> m.year() < 2026).limit(10000);
        Histogram histogram = HistogramBuilder.with(movies).title("Movies per year")
                .x("Year").y("Frequency").legend("Movies").build(Movie::year);
        MainFrame.create().display(histogram).setVisible(true);
    }

    private static Movie fromTsv(String line) {
        String[] parts = line.split("\t");
        return new Movie(parts[2], toInt(parts[5]), toInt(parts[7]));
    }

    private static int toInt(String s) {
        if(s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }

}


