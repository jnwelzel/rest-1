package br.com.geladaonline.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.oauth1.DefaultOAuth1Provider;
import org.glassfish.jersey.server.oauth1.OAuth1ServerFeature;
import org.glassfish.jersey.server.oauth1.OAuth1ServerProperties;

@ApplicationPath("/")
public class ApplicationJAXRS extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages", "br.com.geladaonline.services");
        properties.put(OAuth1ServerProperties.IGNORE_PATH_PATTERN, "requestToken|authorize|accessToken");
        properties.put(OAuth1ServerProperties.ENABLE_TOKEN_RESOURCES, true);
        return properties;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CervejaService.class);
        classes.add(AuthorizeOAuth.class);
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {

        DefaultOAuth1Provider provider = new DefaultOAuth1Provider();
        String IDDoConsumidor = "App consumidora";
        String consumerKey = "123";
        String consumerSecret = "123";

        provider.registerConsumer(IDDoConsumidor, consumerKey, consumerSecret, new MultivaluedStringMap());

        Set<Object> singletons = new HashSet<>();
        singletons.add(new JettisonFeature());
        singletons.add(new OAuth1ServerFeature(provider));

        return singletons;
    }

}
