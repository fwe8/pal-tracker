package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JPATimeEntryRepository implements TimeEntryRepository {
    @Autowired
    private TimeEntryJPARepository rep;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        return rep.save(timeEntry);
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return rep.findById(timeEntryId).orElse(null);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry t = timeEntry.copy();

        if (t.getId() == null) {
            t.setId(id);
        }
        return rep.save(t);
    }

    @Override
    public void delete(long timeEntryId) {
        rep.deleteById(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return rep.findAll();
    }
}
