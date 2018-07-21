package com.mindcanary.controllers;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/dailySheet")
public class DailySheetController {

    @RequestMapping(value = "/{sheetId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody
    String getSheet(@PathVariable("sheetId") int sheetId) {
        String message = "Daily Sheet " + sheetId;
        return message;
    }
}
