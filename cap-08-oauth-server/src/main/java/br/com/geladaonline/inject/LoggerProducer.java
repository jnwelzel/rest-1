package br.com.geladaonline.inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.spi.InjectionPoint;

public class LoggerProducer {

    @Log
    public Logger getLogger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}