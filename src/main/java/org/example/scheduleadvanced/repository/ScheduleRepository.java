package org.example.scheduleadvanced.repository;

import org.example.scheduleadvanced.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findScheduleById(Long id);
    List<Schedule> findAllByUserId(Long userId);
}
