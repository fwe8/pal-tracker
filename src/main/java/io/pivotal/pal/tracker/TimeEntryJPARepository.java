package io.pivotal.pal.tracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeEntryJPARepository extends JpaRepository<TimeEntry, Long> {

}
