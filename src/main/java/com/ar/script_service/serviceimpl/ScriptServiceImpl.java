package com.ar.script_service.serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.ar.script_service.service.ScriptService;



@Service
public class ScriptServiceImpl implements ScriptService {

    private static final Logger logger = LoggerFactory.getLogger(ScriptServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String getScript(String script_id) {
        try {
            // Load the script from the resources directory
            Resource resource = resourceLoader.getResource("classpath:scripts/"+script_id+".sh");

            // Ensure the resource exists and get the file path
            if (!resource.exists()) {
                return "Script file not found!";
            }
            String scriptPath;
        
            scriptPath = resource.getFile().getAbsolutePath();

            return scriptPath;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String saveScript(String script_id, String script) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveScript'");
    }

    @Override
    public String updateScript(String script_id, String script) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateScript'");
    }

    @Override
    public String deleteScript(String script_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteScript'");
    }

    @Override
    public String executeScript(String script_id) {
        try {
            
            logger.info("Fetching script with ID: " + script_id);

            String scriptPath = getScript(script_id);

            logger.info("Executing script: " + scriptPath);

            // Create a ProcessBuilder to run the script
            ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptPath);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the script
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            logger.info("Script output: " + output.toString());

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return "Script executed successfully!\n" + output.toString();
            } else {
                return "Script execution failed with exit code: " + exitCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String executeScriptFromPath(String scriptPath) {
        try {
            logger.info("Executing script: " + scriptPath);

            // Create a ProcessBuilder to run the script
            ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptPath);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the script
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return "Script executed successfully!\n" + output.toString();
            } else {
                return "Script execution failed with exit code: " + exitCode;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
}
