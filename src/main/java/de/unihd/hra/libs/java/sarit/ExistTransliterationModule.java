package de.unihd.hra.libs.java.sarit;

import java.util.List;
import java.util.Map;

import org.exist.xquery.AbstractInternalModule;
import org.exist.xquery.FunctionDef;
import org.sanskritlibrary.ResourceInputStream;
import org.sanskritlibrary.webservice.TransformMap;

import de.unihd.hra.libs.java.luceneTranscodingAnalyzer.TransformMaps;

/**
 * Implements the module definition.
 * 
 * @author Claudius Teodorescu <claudius.teodorescu@gmail.com>
 */
public class ExistTransliterationModule extends AbstractInternalModule {
	
	static {
		ResourceInputStream ris = new TransformMaps.Transcoders();

		try {
			TransformMaps.transformMaps.put("deva2slp1", new TransformMap("*:deva->slp1", ris, TransformMaps.fsmh));
			TransformMaps.transformMaps.put("roman2slp1", new TransformMap("*:roman->slp1", ris, TransformMaps.fsmh));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExistTransliterationModule(Map<String, List<? extends Object>> parameters) {
		super(functions, parameters);
	}

	private final static FunctionDef[] functions = {
			new FunctionDef(TransliterateFunction.signature, TransliterateFunction.class) };

	public static String NAMESPACE_URI = "http://hra.uni-heidelberg.de/ns/sarit-transliteration-exist-module";
	public static String PREFIX = "sarit";

	@Override
	public String getDefaultPrefix() {
		return PREFIX;
	}

	@Override
	public String getDescription() {
		return "A module for performing transliteration between various scripts, by using SLP1 as turnover.";
	}

	@Override
	public String getNamespaceURI() {
		return NAMESPACE_URI;
	}

	@Override
	public String getReleaseVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
