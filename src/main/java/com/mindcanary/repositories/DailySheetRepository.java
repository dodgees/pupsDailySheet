package com.mindcanary.repositories;

import com.mindcanary.domain.dailySheet.DailySheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailySheetRepository extends JpaRepository<DailySheet, Long> {

}
