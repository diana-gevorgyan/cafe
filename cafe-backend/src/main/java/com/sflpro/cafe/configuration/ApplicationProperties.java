package com.sflpro.cafe.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cafe")
public class ApplicationProperties {

    private SecurityProperties security;

    public static class SecurityProperties {

        private String authTokenKey;
        private String jwtSecret;
        private long expirationMillis;
        private String corsAllowAllPattern;

        public String getAuthTokenKey() {
            return authTokenKey;
        }

        public void setAuthTokenKey(String authTokenKey) {
            this.authTokenKey = authTokenKey;
        }

        public String getJwtSecret() {
            return jwtSecret;
        }

        public void setJwtSecret(String jwtSecret) {
            this.jwtSecret = jwtSecret;
        }

        public long getExpirationMillis() {
            return expirationMillis;
        }

        public void setExpirationMillis(long expirationMillis) {
            this.expirationMillis = expirationMillis;
        }

        public String getCorsAllowAllPattern() {
            return corsAllowAllPattern;
        }

        public void setCorsAllowAllPattern(String corsAllowAllPattern) {
            this.corsAllowAllPattern = corsAllowAllPattern;
        }
    }

    public SecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SecurityProperties security) {
        this.security = security;
    }
}
