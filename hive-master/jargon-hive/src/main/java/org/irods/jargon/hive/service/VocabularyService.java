package org.irods.jargon.hive.service;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.irods.jargon.hive.container.HiveContainer;
import org.irods.jargon.hive.exception.VocabularyNotFoundException;
import org.unc.hive.client.ConceptProxy;

import edu.unc.ils.mrc.hive.api.ConceptNode;
import edu.unc.ils.mrc.hive.api.SKOSScheme;
import edu.unc.ils.mrc.hive.api.SKOSSearcher;
import edu.unc.ils.mrc.hive.ir.lucene.search.AutocompleteTerm;

public interface VocabularyService {

	SKOSSearcher getSKOSSearcher();

	long getNumberOfConcept(String vocabularyName)
			throws VocabularyNotFoundException;

	long getNumerOfRelations(String vocabularyName);

	Date getLastUpdateDate(String vocabularyName);

	List<List<String>> getAllVocabularies();

	/**
	 * Return a vocabulary (as a <code>SKOSScheme</code>) based on the name
	 * 
	 * @param vocabularyName
	 *            <code>String</code> with the vocabulary name
	 * @return {@link SKOSScheme} that represents the vocabulary with the given
	 *         name
	 * @throws VocabularyNotFoundException
	 */
	SKOSScheme getVocabularyByName(String vocabularyName)
			throws VocabularyNotFoundException;

	List<String> getAllVocabularyNames();

	HashMap<String, HashMap<String, String>> getVocabularyProperties();

	List<ConceptProxy> getSubTopConcept(String vocabulary, String letter,
			boolean brief) throws VocabularyNotFoundException;

	/**
	 * @gwt.typeArgs <client.ConceptProxy>
	 * 
	 * */

	List<ConceptProxy> getChildConcept(String nameSpaceURI, String localPart);

	/**
	 * @gwt.typeArgs <client.ConceptProxy>
	 * 
	 * */

	Set<ConceptProxy> searchConcept(String keyword,
			List<String> openedVocabularies);

	ConceptProxy getConceptByURI(String namespaceURI, String localPart);

	/**
	 * @gwt.typeArgs <client.ConceptProxy>
	 * 
	 * */
	List<ConceptProxy> getTagsBasedOnFilePath(String inputFilePath,
			List<String> openedVocabularies, int numTerms, String algorithm);

	List<ConceptProxy> getTagsBasedOnFilePath(String input,
			List<String> openedVocabularies, int numTerms, int minPhraseOccur,
			String algorithm);

	Map<String, String> getStringMap(Map<String, QName> qnameMap);

	List<ConceptProxy> getTags(URL url, List<String> openedVocabularies,
			int maxHops, int numTerms, boolean diff, int minOccur,
			String algorithm);

	List<ConceptNode> getTagsAsTree(String text,
			List<String> openedVocabularies, int maxHops, int numTerms,
			String algorithm);

	ConceptProxy getFirstConcept(String vocabulary);

	/**
	 * @param vocabulary
	 * @param str
	 * @param numTerms
	 * @return
	 * @throws Exception
	 */
	List<AutocompleteTerm> suggestTermsFor(String vocabulary, String str,
			int numTerms) throws Exception;

	void close();

	void setHiveContainer(HiveContainer hiveContainer);

	/**
	 * Create a <code>ConceptProxy</code> that represents the top level of a
	 * given vocabulary. This wraps the <code>getTopSubConcept</code> method to
	 * give a consistent view as a <code>ConceptProxy</code>.
	 * 
	 * @param vocabulary
	 *            <code>String</code> with a vocabulary name as stored in HIVE,
	 *            required
	 * @param letter
	 *            <code>String</code> with the index letter. If not specified
	 *            will default to 'A'
	 * @param brief
	 *            <code>boolean</code> that indicates that only basic data is
	 *            retrieved
	 * @return {@link ConceptProxy} marked as 'topLevel'
	 * @throws VocabularyNotFoundException
	 */
	ConceptProxy getConceptProxyForTopOfVocabulary(String vocabulary,
			String letter, boolean brief) throws VocabularyNotFoundException;

}