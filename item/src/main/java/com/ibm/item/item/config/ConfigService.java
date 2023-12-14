package com.ibm.item.item.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ConfigService {
    
    @Value("${configuration.text}")
    String text;

    @Value("${configuration.service.name}")
    String nameEnvironment;

    @Value("${configuration.author.name}")
    String author;

    @Value("${configuration.author.email}")
    String email;

    public Map<String, String> getConfig(){
        Map<String, String> json = new HashMap<String, String>();
        json.put("name", nameEnvironment);
        json.put("description", text);
        json.put("author", author);
        json.put("email", email);
        return json;
    }
}
