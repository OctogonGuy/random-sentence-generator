package tech.octopusdragon.randomsentencegenerator.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * A custom version of the Properties class that maintains the order in which
 * properties are read from an input stream.
 * @author Alex Gill
 *
 */
public class OrderedProperties extends Properties {

	private static final long serialVersionUID = -6345054035634925980L;
	
	
	private Map<Object, Object> linkedMap = new LinkedHashMap<Object, Object>();
	
	
	public OrderedProperties() {
		super();
	}
	
	
	public OrderedProperties(Properties defaults) {
		this.defaults = defaults;
	}

	
	
	// --- Hashtable methods ---
	
	@Override
	public int size() {
		return linkedMap.size();
	}
	
	@Override
	public Object get(Object key) {
		return linkedMap.get(key);
	}
	
	
	@Override
	public Object put(Object key, Object value) {
		return linkedMap.put(key, value);
	}
	
	
	@Override
	public boolean contains(Object value) {
		return linkedMap.containsValue(value);
	}
	
	
	@Override
	public boolean containsValue(Object value) {
		return linkedMap.containsValue(value);
	}
	
	
	@Override
	public Enumeration<Object> keys() {
		return Collections.enumeration(linkedMap.keySet());
	}
	
	
	@Override
	public Enumeration<Object> elements() {
		throw new UnsupportedOperationException("Don't use elements(). "
				+ "Use keySet() or entrySet() instead");
	}
	
	
	@Override
	public Set<Object> keySet() {
		return linkedMap.keySet();
	}
	
	
	@Override
	public Set<Entry<Object, Object>> entrySet() {
		return linkedMap.entrySet();
	}
	
	
	@Override
	public void clear() {
		linkedMap.clear();
	}
	
	
	@Override
	public boolean containsKey(Object key) {
		return linkedMap.containsKey(key);
	}
	
	
	@Override
	public String toString() {
		return linkedMap.toString();
	}
	
	
	
	// --- Properties methods ---
	
	@Override
	public String getProperty(String key) {
        Object oval = get(key);
        String sval = (oval instanceof String) ? (String)oval : null;
        return ((sval == null) && (defaults != null)) ?
        		defaults.getProperty(key) :
        		sval;
	}
	
}
