package me.yeaton.mediatracker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.yeaton.mediatracker.model.Series;
import me.yeaton.mediatracker.service.SeriesService;

@RestController
@RequestMapping("api/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    // Create
    @PostMapping
    public Series createSeries(@RequestBody Series series) {
        return seriesService.createSeries(series);
    }

    // Read
    @GetMapping
    public Iterable<Series> fetchAllSeries() {
        return seriesService.fetchAllSeries();
    }

    // Update
    @PutMapping("/{id}")
    public Series updateSeries(@RequestBody Series series, @PathVariable("id") UUID id) {
        return seriesService.updateSeries(series, id);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable("id") UUID id) {
        seriesService.deleteSeries(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
}
