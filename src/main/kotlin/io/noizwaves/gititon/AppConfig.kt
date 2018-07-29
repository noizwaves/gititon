package io.noizwaves.gititon

import org.eclipse.jgit.http.server.GitServlet
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.file.Files


@Configuration
class AppConfig {

    @Bean
    fun servletRegistrationBean(): ServletRegistrationBean {
        val repos = Files.createTempDirectory("repos")

        val servletRegistrationBean = ServletRegistrationBean(GitServlet(), "/git/*")
        servletRegistrationBean.addInitParameter("base-path", repos.toString())
        servletRegistrationBean.addInitParameter("export-all", "true")
        return servletRegistrationBean
    }
}
