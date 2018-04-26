package es.upm.fi.dia.oeg.mappingpedia;

import java.io.IOException;
import java.io.InputStream;

//import org.apache.jena.ontology.OntModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartProperties;

import es.upm.fi.dia.oeg.mappingpedia.utility.CKANUtility;
import es.upm.fi.dia.oeg.mappingpedia.utility.GitHubUtility;
import es.upm.fi.dia.oeg.mappingpedia.utility.JenaClient;
import es.upm.fi.dia.oeg.mappingpedia.utility.VirtuosoClient;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger("Application");
		logger.info("Working Directory = " + System.getProperty("user.dir"));
		logger.info("Starting MappingPedia WS version 0.9.2 ...");

		/*
		InputStream is = null;
		String configurationFilename = "config.properties";
		try {

			logger.info("Loading configuration file ...");
			is = Application.class.getClassLoader().getResourceAsStream(configurationFilename);
			if(is==null){
				logger.error("Sorry, unable to find " + configurationFilename);
				return;
			}
			MappingPediaProperties properties = new MappingPediaProperties(is);
			properties.load(is);
			logger.info("Configuration file loaded.");
			MappingPediaEngine.init(properties);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		*/

		MappingPediaProperties properties = MappingPediaProperties.apply();
		MappingPediaEngine.init(properties);
		SpringApplication.run(Application.class, args);

		MultipartProperties multipartProperties = new MultipartProperties();
		multipartProperties.setLocation("./mappingpediaenginetemporarydirectory");
		String multiPartPropertiesLocation = multipartProperties.getLocation();
		logger.info("multiPartPropertiesLocation = " + multiPartPropertiesLocation);

		logger.info("Mappingpedia WS started.\n\n\n");
	}
}
