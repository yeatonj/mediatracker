package me.yeaton.mediatracker.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.yeaton.mediatracker.model.Series;
import me.yeaton.mediatracker.repository.SeriesRepository;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    // Create
    public Series createSeries(Series series) {
        return seriesRepository.save(series);
    }

    // Read
    public Iterable<Series> fetchAllSeries() {
        return seriesRepository.findAll();
    }

    // Update
    public Series updateSeries(Series series, UUID id) {
        Series seriesDB = seriesRepository.findById(id).get();
        if (Objects.nonNull(series.getName()) && !"".equalsIgnoreCase(series.getName())) {
            seriesDB.setName(series.getName());
        }
        return seriesRepository.save(seriesDB);
    }

    // Delete
    public void deleteSeries(UUID id) {
        seriesRepository.deleteById(id);
    }
}
