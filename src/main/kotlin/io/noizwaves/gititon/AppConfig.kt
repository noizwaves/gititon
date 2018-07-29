package io.noizwaves.gititon

import org.codehaus.plexus.archiver.tar.TarGZipUnArchiver
import org.eclipse.jgit.http.server.GitServlet
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
        val archiver = TarGZipUnArchiver(File(repoArtifact.toURI()))
        archiver.destDirectory = File(repoPath.toUri())
        archiver.extract()

        // Set JGit parameters
        val servletRegistrationBean = ServletRegistrationBean(GitServlet(), "/code/*")
        servletRegistrationBean.addInitParameter("base-path", basePath.toString())
        servletRegistrationBean.addInitParameter("export-all", "true")
        return servletRegistrationBean
    }
}
