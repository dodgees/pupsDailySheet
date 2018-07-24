package com.mindcanary.domain.dailySheet;

import com.mindcanary.repositories.DailySheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class DailySheetServiceImpl implements DailySheetService {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Inject
    private DailySheetRepository dailySheetRepository;

    public DailySheet saveDailySheet(String dailySheetTitle){
        Date date = new Date();

        DailySheet dailySheet = new DailySheet(dailySheetTitle, new Date());
        DailySheet savedDailySheet = dailySheetRepository.save(dailySheet);
        return savedDailySheet;
    }
}
