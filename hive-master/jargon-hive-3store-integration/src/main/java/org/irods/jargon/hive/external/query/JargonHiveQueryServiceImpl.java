package org.irods.jargon.hive.external.query;

import java.util.HashMap;
import java.util.Map;

import org.irods.jargon.core.connection.IRODSAccount;
import org.irods.jargon.core.pub.IRODSAccessObjectFactory;
import org.irods.jargon.core.service.AbstractJargonService;
import org.irods.jargon.hive.external.sparql.HiveQueryException;
import org.irods.jargon.hive.external.sparql.JenaHiveSPARQLService;
import org.irods.jargon.hive.external.sparql.JenaHiveSPARQLServiceImpl;
import org.irods.jargon.hive.external.utils.HiveException;
import org.irods.jargon.hive.external.utils.JenaHiveConfiguration;
import org.irods.jargon.hive.external.utils.template.HiveTemplateException;
import org.irods.jargon.hive.external.utils.template.SPARQLTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Support basic queries for HIVE-annotated data, this wraps the
 * {@link JenaHiveSPARQLService}, which allows direct execution of arbitrary
 * SPARQL queries.
 * 
 * @author Mike Conway - DICE (www.irods.org)
 * 
 */
public class JargonHiveQueryServiceImpl extends AbstractJargonService implements
		JargonHiveQueryService {

	public static final Logger log = LoggerFactory
			.getLogger(JargonHiveQueryServiceImpl.class);
	private final JenaHiveConfiguration jenaHiveConfiguration;
	private final JenaHiveSPARQLService jenaHiveSPARQLService;

	public static final String SPARQL_ALL_FOR_TERM = "/sparql-template/queryAllForTerm.txt";

	public static final String TERM = "term";

	/**
	 * @param irodsAccessObjectFactory
	 * @param irodsAccount
	 * @param jenaHiveConfiguration
	 */
	public JargonHiveQueryServiceImpl(
			final IRODSAccessObjectFactory irodsAccessObjectFactory,
			final IRODSAccount irodsAccount,
			final JenaHiveConfiguration jenaHiveConfiguration)
			throws HiveException {
		super(irodsAccessObjectFactory, irodsAccount);

		if (jenaHiveConfiguration == null) {
			throw new IllegalArgumentException("null jenaHiveConfiguration");
		}

		this.jenaHiveConfiguration = jenaHiveConfiguration;
		jenaHiveSPARQLService = new JenaHiveSPARQLServiceImpl(
				irodsAccessObjectFactory, irodsAccount, jenaHiveConfiguration);
		jenaHiveSPARQLService.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.irods.jargon.hive.external.query.JargonHiveQueryService#queryForUri
	 * (java.lang.String)
	 */
	@Override
	public String queryForUri(final String vocabularyUri)
			throws HiveQueryException {

		log.info("queryForUri(final String vocabularyUri)");

		if (vocabularyUri == null || vocabularyUri.isEmpty()) {
			throw new IllegalArgumentException("null or empty vocabularyUri");
		}

		log.info("vocabularyUri:{}", vocabularyUri);

		Map<String, String> params = new HashMap<String, String>();
		params.put(TERM, vocabularyUri);
		try {
			String query = SPARQLTemplateUtils
					.getSPARQLTemplateAndSubstituteValues(SPARQL_ALL_FOR_TERM,
							params);
			log.info("built query:{}", query);
			return jenaHiveSPARQLService.queryAndReturnJSONAsString(query);
		} catch (HiveTemplateException e) {
			throw new HiveQueryException(
					"error making query from sparql template", e);
		}
	}

	/**
	 * @return the jenaHiveConfiguration
	 */
	public JenaHiveConfiguration getJenaHiveConfiguration() {
		return jenaHiveConfiguration;
	}

}
