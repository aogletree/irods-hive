/**
 * 
 */
package org.irods.jargon.hive.rest.vocabservice;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.irods.jargon.hive.exception.JargonHiveException;
import org.irods.jargon.hive.service.VocabularyService;
import org.irods.jargon.hive.service.domain.VocabularyInfo;
import org.jboss.resteasy.annotations.providers.jaxb.json.Mapped;
import org.jboss.resteasy.annotations.providers.jaxb.json.XmlNsMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * REST wrapper for vocabulary service allowing query of HIVE terms and
 * navigation of stored vocabularies
 * 
 * @author Mike Conway
 * 
 */
@Named
@Path("/vocabulary")
public class RestVocabularyService {
	/**
	 * Injected dependency on the HIVE vocabulary service
	 */
	private VocabularyService vocabularyService;

	public static final Logger log = LoggerFactory
			.getLogger(RestVocabularyService.class);

	public VocabularyService getVocabularyService() {
		return vocabularyService;
	}

	@Autowired
	public void setVocabularyService(VocabularyService vocabularyService) {
		this.vocabularyService = vocabularyService;
	}

	/**
	 * respond to a get request and return a list of vocabularies
	 * 
	 * @return <code>List<String></code> with vocabulary names in the HIVE
	 * @throws JargonHiveException
	 */
	@GET
	@Produces({ "application/xml", "application/json" })
	@Mapped(namespaceMap = { @XmlNsMap(namespace = "http://irods.org/hive", jsonName = "hive-vocabulary-service-rest") })
	public List<VocabularyInfo> getVocabularies() throws JargonHiveException {
		log.info("getVocabularies()");
		return vocabularyService.getAllVocabularies();

	}

}