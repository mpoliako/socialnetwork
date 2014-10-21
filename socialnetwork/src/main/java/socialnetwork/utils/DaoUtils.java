package socialnetwork.utils;

import socialnetwork.model.daofactory.DaoFactory;

public final class DaoUtils {
	
	private static DaoFactory daoFactory;

	public static DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public static void setDaoFactory(DaoFactory daoFactory) {
		DaoUtils.daoFactory = daoFactory;
	}	
	

}
