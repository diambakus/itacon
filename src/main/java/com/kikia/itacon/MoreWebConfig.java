package com.kikia.itacon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * Beside security stuff, deploy and dependencies items, we're making other
 * configuration such as thymeleaf dialect layout
 * 
 * @author diambakus
 *
 */
@Configuration
public class MoreWebConfig extends WebMvcConfigurerAdapter {

	/**
	 * Configuration of thymeleaf fragment Layout
	 * 
	 * @return
	 */
	@Bean
	public TemplateEngine getTemplateEngine() {
		TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.addDialect(new LayoutDialect());

		return templateEngine;
	}
}
