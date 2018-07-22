package com.mindcanary.domain.dailySheet;

import com.mindcanary.repositories.DailySheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class DailySheetServiceImpl implements DailySheetService {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private DailySheetRepository dailySheetRepository;

    public DailySheetServiceImpl(DailySheetRepository dailySheetRepository){
        this.dailySheetRepository = dailySheetRepository;
    }

    public DailySheet saveDailySheet(String dailySheetTitle){
        Date date = new Date();

        DailySheet dailySheet = new DailySheet(1L, dailySheetTitle, new Date());
        DailySheet savedDailySheet = dailySheetRepository.save(dailySheet);
        return savedDailySheet;
    }
}