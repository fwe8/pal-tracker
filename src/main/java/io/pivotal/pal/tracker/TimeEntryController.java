package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;


    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(@Autowired TimeEntryRepository timeEntryRepository,
                               MeterRegistry meterRegistry) {
        this.timeEntryRepository = timeEntryRepository;

        // Summary speichert die aktuelle Anzahl an Elementen im Repo
        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        // Actioncounter speichert die Anzahl an Actions, die durchgeführt wurden
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry createdEntry = timeEntryRepository.create(timeEntryToCreate);

        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());

        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }
    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable("timeEntryId") long timeEntryId) {
        TimeEntry foundEntry = timeEntryRepository.find(timeEntryId);
        actionCounter.increment();
        return new ResponseEntity<>(foundEntry, foundEntry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry foundEntry = timeEntryRepository.update(timeEntryId, timeEntry);
        actionCounter.increment();
        return new ResponseEntity<>(foundEntry, foundEntry == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        actionCounter.increment();
        timeEntrySummary.record(timeEntryRepository.list().size());

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
