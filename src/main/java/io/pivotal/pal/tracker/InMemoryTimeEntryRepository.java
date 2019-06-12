package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> data = new HashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if (timeEntry.getId() != null)
            throw new IllegalArgumentException("TimeEntry already has an id");
        TimeEntry t = timeEntry.copy();
        t.setId(nextId.getAndIncrement());
        data.put(t.getId(), t);
        return t;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return data.get(timeEntryId);
    }


    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntry.getId() != null && timeEntry.getId() != id)
            // throw new IllegalArgumentException("Id of timeEntry does not match requested update id");
            return null;
        if (this.find(id) == null)
            // throw new IllegalArgumentException("Id does not exist");
            return null;
        TimeEntry t = timeEntry.copy();
        t.setId(id);
        data.put(id, t);
        return t;
    }

    @Override
    public void delete(long timeEntryId) {
        data.remove(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(data.values());
    }


}
