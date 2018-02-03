package com.walmartlabs.sca.kafka.router;

import org.apache.camel.component.servletlistener.CamelContextLifecycle;
import org.apache.camel.component.servletlistener.ServletCamelContext;
import org.apache.camel.impl.JndiRegistry;

public class RouterLifecycle implements CamelContextLifecycle<JndiRegistry> {
	@Override
	public void beforeStart(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// enlist our bean(s) in the registry
		//registry.bind("myBean", new HelloBean());
	}

	@Override
	public void beforeStop(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// noop
	}

	@Override
	public void afterStop(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// noop
	}

	@Override
	public void beforeAddRoutes(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// noop
	}

	@Override
	public void afterAddRoutes(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// noop
	}

	@Override
	public void afterStart(ServletCamelContext camelContext, JndiRegistry registry) throws Exception {
		// noop
	}
}
