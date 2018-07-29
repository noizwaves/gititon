package io.noizwaves.gititon

import org.eclipse.jgit.http.server.GitServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AppConfig {

    @Bean
    fun servletRegistrationBean(): ServletRegistrationBean {
        val servletRegistrationBean = ServletRegistrationBean(GitServlet(), "/git/*")
        servletRegistrationBean.addInitParameter("base-path", "/Users/adam/workspace/gititon/sandbox/base")
        servletRegistrationBean.addInitParameter("export-all", "true")
        return servletRegistrationBean
    }
}
