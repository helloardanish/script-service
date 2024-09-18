package com.ar.script_service.service;

public interface ScriptService {
    String getScript(String script_id);
    String saveScript(String script_id, String script);
    String updateScript(String script_id, String script);
    String deleteScript(String script_id);
    String executeScript(String script_id);
    String executeScriptFromPath(String scriptPath);
}
