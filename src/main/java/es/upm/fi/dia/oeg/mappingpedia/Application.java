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
		logger.info("Starting MappingPedia Engine version 0.9.1 ...");

		InputStream is = null;
		String configurationFilename = "config.properties";
		try {

			logger.info("Loading configuration file ...");
			//String filename="config.properties";
			is = Application.class.getClassLoader().getResourceAsStream(configurationFilename);
			if(is==null){
				logger.error("Sorry, unable to find " + configurationFilename);
				return;
			}
			MappingPediaProperties properties = new MappingPediaProperties(is);
			properties.load(is);
			logger.info("Configuration file loaded.");
			MappingPediaEngine.init(properties);
			/*
			MappingPediaEngine.setProperties(properties);

			if(properties.githubEnabled()) {
				try {
					GitHubUtility githubClient = new GitHubUtility(properties.githubRepository(), properties.githubUser()
							, properties.githubAccessToken()
					);
					//logger.info(" githubClient = " + githubClient);
					MappingPediaEngine.githubClient_$eq(githubClient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(properties.ckanEnable()) {
				try {
					CKANUtility ckanClient = new CKANUtility(properties.ckanURL(), properties.ckanKey());
					//logger.info(" ckanClient = " + ckanClient);
					MappingPediaEngine.ckanClient_$eq(ckanClient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}



			if(properties.virtuosoEnabled()) {
				VirtuosoClient virtuosoClient = null;

				try {
					 virtuosoClient = new VirtuosoClient(properties.virtuosoJDBC(), properties.virtuosoUser()
							, properties.virtuosoPwd(), properties.graphName()
					);
					//logger.info(" virtuosoClient = " + virtuosoClient);
					MappingPediaEngine.virtuosoClient_$eq(virtuosoClient);
				} catch(Exception e) {
					e.printStackTrace();
				}

				try {
					OntModel schemaOntology = JenaClient.loadSchemaOrgOntology(
							virtuosoClient,
							MappingPediaConstant.SCHEMA_ORG_FILE(), MappingPediaConstant.FORMAT());
					MappingPediaEngine.setOntologyModel(schemaOntology);
					JenaClient jenaClient = new JenaClient(schemaOntology);
					//logger.info(" jenaClient = " + jenaClient);
					MappingPediaEngine.jenaClient_$eq(jenaClient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			*/





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

		SpringApplication.run(Application.class, args);

		MultipartProperties multipartProperties = new MultipartProperties();
		multipartProperties.setLocation("./mappingpediaenginetemporarydirectory");
		String multiPartPropertiesLocation = multipartProperties.getLocation();
		logger.info("multiPartPropertiesLocation = " + multiPartPropertiesLocation);

		logger.info("Mappingpedia engine started.\n\n\n");
	}
}
