package dev.mbo.springkotlinjpa

import dev.mbo.springkotlinjpa.repository.ExtendedJpaRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.fromApplication
import org.springframework.boot.with
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EntityScan(basePackageClasses = [TestApplication::class])
@EnableJpaRepositories(
    repositoryBaseClass = ExtendedJpaRepositoryImpl::class
)
@SpringBootApplication
open class TestApplication

fun main(args: Array<String>) {
    fromApplication<TestApplication>().with(TestcontainersConfiguration::class).run(*args)
}
