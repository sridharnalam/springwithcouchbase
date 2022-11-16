package com.playground.config;

import com.playground.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.Collections;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${app.couchbase.connection-string}")
    private String connectionString;

    @Value("${app.couchbase.user-name}")
    private String userName;

    @Value("${app.couchbase.password}")
    private String password;

    @Value("${app.couchbase.primary-bucket}")
    private String primaryBucket;

    @Value("${app.couchbase.user-bucket}")
    private String userBucket;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return primaryBucket;
    }

    @Override
    protected void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        mapping.mapEntity(User.class, getCouchbaseTemplate(userBucket));
//        mapping.mapEntity(User.class, getCouchbaseTemplate("employ"));
    }

    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) {
        CouchbaseTemplate template = null;
        try {
            template = new CouchbaseTemplate(
                    getCouchbaseClientFactory(bucketName),
                    mappingCouchbaseConverter(
                          couchbaseMappingContext(customConversions()),
                          new CouchbaseCustomConversions(Collections.emptyList())
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return template;
    }

    private CouchbaseClientFactory getCouchbaseClientFactory(String bucketName) {
        return new SimpleCouchbaseClientFactory(
                couchbaseCluster(couchbaseClusterEnvironment()),
                bucketName,
                getScopeName());
    }
}
