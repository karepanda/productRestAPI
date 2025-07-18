package org.application.core.producttestapi.healthChecks;

import org.springframework.boot.actuate.info.BuildInfoContributor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
public class InfoContributor extends BuildInfoContributor {
    @Override
    public void contribute(org.springframework.boot.actuate.info.Info.Builder builder) {
        builder.withDetail("name", "Product Test API")
               .withDetail("version", "1.0.0")
               .withDetail("description", "API for managing products in the Product Test application");
    }

    public InfoContributor(BuildProperties properties) {
        super(properties);
    }
}
