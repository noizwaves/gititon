package io.noizwaves.gititon

import org.eclipse.jgit.http.server.GitServlet
import org.rauschig.jarchivelib.ArchiverFactory
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


@Configuration
class AppConfig {

    @Bean
    fun servletRegistrationBean(): ServletRegistrationBean {
        val basePath = Files.createTempDirectory("repos")

        // Create dir named repo.git
        val repoPath = Paths.get(basePath.toString(), "repo.git")
        Files.createDirectories(repoPath)

        // Grab the repo artifact to serve
        val repoArtifact = javaClass.classLoader.getResource("repo.tar.gz")

        // Extract artifact to repo dir
        val archive = File(repoArtifact.toURI())
        val destination = File(repoPath.toUri())
        ArchiverFactory
                .createArchiver("tar", "gz")
                .extract(archive, destination)

        // Set JGit parameters
        val servletRegistrationBean = ServletRegistrationBean(GitServlet(), "/code/*")
        servletRegistrationBean.addInitParameter("base-path", basePath.toString())
        servletRegistrationBean.addInitParameter("export-all", "true")
        return servletRegistrationBean
    }
}
