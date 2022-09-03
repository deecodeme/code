package com.poc.code.poc.dbs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/poc/db/{db}")
public class WriteController {

    private final DBFactory dbFactory;

    public WriteController(DBFactory dbFactory) {
        this.dbFactory = dbFactory;
    }

    @PostMapping("/write")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public Object write(@PathVariable("db") String db, @RequestBody Object data) {
        return this.dbFactory.getDatabase(DB.deSerialise(db)).insert(data);
    }
}
