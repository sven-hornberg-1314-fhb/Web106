dataSource {
    pooled = true
    
    
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'org.hibernate.cache.EhCacheProvider'
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
//    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
}

// environment specific settings
environments {
    development {
        dataSource {
	    driverClassName = "org.h2.Driver"
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
	    username = "sa"
    	    password = ""
        }
    }
    test {
        dataSource {
		driverClassName = "org.h2.Driver"
		username = "sa"
    	    password = ""
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }

    production {
        dataSource {
            url = System.getProperty("JDBC_CONNECTION_STRING")
            driverClassName = "com.mysql.jdbc.Driver"
            dbCreate = "update"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            username = System.getProperty("PARAM1")
            password = System.getProperty("PARAM2")

            //configure DBCP
            properties {
                minEvictableIdleTimeMillis=1800000
                timeBetweenEvictionRunsMillis=1800000
                numTestsPerEvictionRun=3
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true

                validationQuery="SELECT 1"
            }
        }
    }
}
