package com.mindcanary.controllers;

import com.mindcanary.domain.dailySheet.DailySheet;
import com.mindcanary.domain.dailySheet.DailySheetService;
import com.mindcanary.domain.dailySheet.DailySheetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/dailySheet")
public class DailySheetController {

    @Autowired
    private DailySheetServiceImpl dailySheetService;

    public DailySheetController(DailySheetServiceImpl dailySheetService){
        this.dailySheetService = dailySheetService;
    }

    @RequestMapping(value = "/{sheetId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getSheet(@PathVariable("sheetId") int sheetId) {
        String message = "Daily Sheet " + sheetId;
        return message;
    }

    @PostMapping(value = "/{sheetTitle}")
    public @ResponseBody String createSheet(@PathVariable("sheetTitle") String sheetTitle) {
        DailySheet savedDailySheet = dailySheetService.saveDailySheet(sheetTitle);
        System.out.println("debug");
        return "redirect:/dailySheet/" + savedDailySheet.getId();
    }
}
