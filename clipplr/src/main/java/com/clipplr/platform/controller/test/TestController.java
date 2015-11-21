package com.clipplr.platform.controller.test;

import com.clipplr.platform.common.core.conversion.annotation.Bounds;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by simon on 15. 6. 26.
 */
@RestController
@RequestMapping(value = "/api/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

    @ApiOperation(value = "Echoes the message that service receive from a user.")
    @RequestMapping(value = "/echo/msg", method = RequestMethod.GET)
    @ResponseBody
    public String echo(@ApiParam(required = false, value = "A message will be echoed.") @RequestParam(required = false) String msg) {
        if (msg == null) {
            return "Null value is not welcome!";
        }

        return msg;
    }


    @ApiOperation(value = "Echoes RowBounds value that service receive from a user. Default MinLimit and MaxLimit")
    @RequestMapping(value = "/echo/rowbounds", method = RequestMethod.GET)
    @ResponseBody
    public RowBounds echoRowBounds(
            @ApiParam(required = false, value = "RowBounds value will be echoed.") @RequestParam(required = false) @Bounds RowBounds bounds) {
        if (bounds == null) {
            return new RowBounds();
        }
        return bounds;
    }

    @ApiOperation(value = "Echoes Date value that service receive from a user.")
    @RequestMapping(value = "/echo/date", method = RequestMethod.GET)
    @ResponseBody
    public Date echoDate(@ApiParam(required = false, value = "Date value will be echoed.") @RequestParam(required = false)
    /* DateTimeFormat (iso  = ISO DATE_TIME)*/Date date) {
        if (date == null) {
            return new Date();
        }

        return date;
    }

}
