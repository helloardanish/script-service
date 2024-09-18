package com.ar.script_service.pojo;

public class ScriptRequest {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ScriptRequest(String path) {
        this.path = path;
    }
    

    public ScriptRequest() {
    }

    @Override
    public String toString() {
        return "ScriptRequest [path=" + path + "]";
    }
}
