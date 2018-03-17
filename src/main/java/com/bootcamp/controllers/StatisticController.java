package com.bootcamp.controllers;

import com.bootcamp.helpers.EntryInfos;
import com.bootcamp.helpers.OutPut;
import com.bootcamp.helpers.T;
import com.bootcamp.services.StatisticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author bignon
 */
@RestController("StatisticController")
@RequestMapping("/stats")
@Api(value = "Global Statistic API", description = "Global Statistic API")
@CrossOrigin(origins = "*")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    /**
     *
     * @return
     * @throws IOException
     */
    @RequestMapping( method = RequestMethod.POST)
    @ApiOperation(value = "all the stats ", notes = "Get stats information on particular enity")
    public ResponseEntity<List<OutPut>> getDebatInfoByEntity(@RequestBody @Valid EntryInfos entryInfos) throws Exception {
       // SimpleDateFormat simpleDate = new SimpleDateFormat("dd-mm-yyyy");

        String entityType = entryInfos.getEntityType();

        int pas = entryInfos.getT().getT();
        String unite = entryInfos.getT().getUnite();

        List<OutPut> outPutInfos = statisticService.getAllByEntityType(entityType,entryInfos.getStartDate(),entryInfos.getEndDate(),pas,unite);

        return new ResponseEntity<>( outPutInfos, HttpStatus.OK );

    }
}
