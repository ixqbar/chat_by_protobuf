package com.xingqiba.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xingqiba.exception.ConfigException;

public class Config {
	private final static Log log = LogFactory.getLog(Config.class);
	private static Map<String, String> setting = new ConcurrentHashMap<String, String>();
	
	
	static {
		iniSetting();
	}

	public Config() {

	}

	/**
	 * 初始化加载配置文件
	 * 
	 * @throws FileNotFoundException
	 */
	public static synchronized void iniSetting() {
		File file;
		file = new File("config/config.properties");
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			Properties p = new Properties();
			p.load(in);
			Enumeration<?> item = p.propertyNames();
			while (item.hasMoreElements()) {
				String key = (String) item.nextElement();
				setting.put(key, p.getProperty(key));
			}
			in.close();
		} catch (FileNotFoundException e) {
			log.error("config file not found at" + file.getAbsolutePath());
			throw new ConfigException("FileNotFoundException", e);
		} catch (IOException e) {
			log.error("config file not found at" + file.getAbsolutePath());
			throw new ConfigException("IOException", e);
		} catch (Exception e) {
			throw new ConfigException("Exception", e);
		}
	}

	public static void reload() {
		try {
			iniSetting();
		} catch (ConfigException e) {
			throw new ConfigException(e.getMessage(), e);
		}
	}

	/**
	 * 获取配置文件的某个键值的配置信息
	 * 
	 * @param key 键
	 * @return 值
	 */
	public static String getSetting(String key) {
		return setting.get(key);
	}

	/**
	 * 设置配置文件的数据
	 * 
	 * @param key
	 * @param value
	 */
	public static void setSetting(String key, String value) {
		setting.put(key, value);
	}
}
