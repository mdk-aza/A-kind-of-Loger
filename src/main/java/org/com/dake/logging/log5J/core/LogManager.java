package org.com.dake.logging.log5J.core;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class LogManager {
	public static final String ROOT_LOGGER_NAME = "";
	// TODO　
	private static final String LOGGER_RESOURCE = "META-INF/log4J-provider.xml";
	private static final String LOGGERT_CONTEXT_FACTORY = "LoggerContextFactory";
	private static final String API_VERSION = "Log4JAPIVersion";
	private static final String[] COMPATIBLE_API_VERSIONS = { "1.99.0" };
	private static LoggerContextFactory factory;

	private static Logger logger = StatsLogger.getLogger();

	// TODO 引数なしのコンストラクタ。また、どこかで継承される可能性があるか確認する
	protected LogManager() {
	}

	static {
		ClassLoader cl = findClassLodaer();
		List<LoggerContextFactory> factories = new ArrayList<>();

		Enumeration enumResources = null;
		try {
			// TODO　非推奨の型を取ってきて、キャストしている。同期化が必要だから残している可能性があるから
			enumResources = cl.getResources(LOGGER_RESOURCE);
		} catch (IOException e) {
			// TODO 後で実装。
			logger.fatal("Unable to locate" + LOGGER_RESOURCE, e);
		}
		//TODO 独自の実装でenumからIteratorに変えている。
		//URL url = (URL) enumResources.nextElement();
		Iterator<?> itr = (Iterator) enumResources;
		if (itr != null) {
			while(itr.hasNext()){
				Properties props = new Properties();
				URL url = (URL) itr.next();
				//TODO ループの中でTry~catchが使われている。
				try{
					props.loadFromXML(url.openStream());
				}catch(IOException ioe){
				//TODO 後で実装
					logger.error("Unload to read" + url.toString(), ioe);
				}
				//TODO 後で実装
				if(!validVersion(props.getProperty(API_VERSION))){
					continue;
				}
				String className = props.getProperty(LOGGERT_CONTEXT_FACTORY);
			}
		}

	}

	private static ClassLoader findClassLodaer() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	private static boolean validVersion(String property) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
