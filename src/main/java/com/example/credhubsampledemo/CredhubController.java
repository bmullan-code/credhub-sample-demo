package com.example.credhubsampledemo;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredhubController {

	@RequestMapping("/all")
	public Map<String,String> allEnvs() {
		Map<String, String> env = System.getenv();
        // Java 8
        //env.forEach((k, v) -> System.out.println(k + ":" + v));

        // Classic way to loop a map
        for (Map.Entry<String, String> entry : env.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        return env;
	}
	
}
