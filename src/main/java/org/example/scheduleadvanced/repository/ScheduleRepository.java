package org.example.scheduleadvanced.repository;

import org.example.scheduleadvanced.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllById(Long id);
    void deleteScheduleById(Long id);
    Schedule findScheduleById(Long id);
    List<Schedule> findAllByUserId(Long userId);
}
