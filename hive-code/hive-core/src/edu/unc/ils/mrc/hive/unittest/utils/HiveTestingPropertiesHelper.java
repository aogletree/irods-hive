package edu.unc.ils.mrc.hive.unittest.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.irods.jargon.testutils.TestingUtilsException;

/**
 * Helper classes for testing
 * 
 * @author Mike Conway - DICE (www.irods.org)
 * 
 */
public class HiveTestingPropertiesHelper {

	/**
	 * Dir for the preconfigured 'functional' testing hive built using the
	 * HiveTestInstanceSetup utility
	 */
	public static final String TEST_HIVE_PARENT_DIR = "test.hive.parent.dir";

	/**
	 * Location of a source HIVE set of RDF and template config properties
	 */
	public static final String TEST_HIVE_SOURCE_DIR = "test.hive.source.dir";

	/**
	 * Location of a temp scratch directory under which any number of test HIVEs
	 * can be built in different unit and funtional tests
	 */
	public static final String TEST_HIVE_SCRATCH_DIR = "test.hive.scratch.dir";

	/**
	 * 
	 */
	public HiveTestingPropertiesHelper() {
	}

	/**
	 * Load the properties that control various tests from the
	 * testing.properties file on the code path
	 * 
	 * @return <code>Properties</code> class with the test values
	 * @throws TestingUtilsException
	 */
	public Properties getTestProperties() throws TestingUtilsException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream in = loader.getResourceAsStream("hive.testing.properties");
		Properties properties = new Properties();

		try {
			properties.load(in);
		} catch (IOException ioe) {
			throw new TestingUtilsException("error loading test properties",
					ioe);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				// ignore
			}
		}

		return properties;
	}

}
