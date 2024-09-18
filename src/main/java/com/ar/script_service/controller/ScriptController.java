package com.ar.script_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ar.script_service.service.ScriptService;



@RestController
@RequestMapping("/scripts")
public class ScriptController {

    private static final Logger logger = LoggerFactory.getLogger(ScriptController.class);

    @Autowired
    private ScriptService scriptService;

    // Run from scripts inside resources directory enclosed with project
    // http://localhost:8080/scripts/run/1
    @GetMapping("/run/{script_id}")
    public String runScript(@PathVariable String script_id) {
        // Map script_id to script path
        logger.info("Script ID: "+script_id);
        if(script_id.isEmpty() || script_id == null) {
            return "Script is empty!";
        }
        String prefix = "angular8_";
        if(script_id.equals("test")) {
            script_id = "test";
        }else {
            script_id = prefix + script_id;
        }
        return scriptService.executeScript(script_id);
    }

    // http://localhost:8080/scripts/run?path=/home/a-r-danish/A_R/2.sh
    @GetMapping("/run")
    public String runPath(@RequestParam String path) {
        return scriptService.executeScriptFromPath(path);
    }

    // http://localhost:8080/scripts/run >> body >> /home/a-r-danish/A_R/2.sh
    // @PostMapping("/run")
    // public String runPath2(@RequestBody String path) {
    //     return scriptService.executeScriptFromPath(path);
    // }


    // http://localhost:8080/scripts/run >> body >> /home/a-r-danish/A_R/2.sh
    // @PostMapping("/run")
    // public String runPath3(@RequestBody ScriptRequest scriptRequest) {
    //     return scriptService.executeScriptFromPath(scriptRequest.getPath());
    // }
}