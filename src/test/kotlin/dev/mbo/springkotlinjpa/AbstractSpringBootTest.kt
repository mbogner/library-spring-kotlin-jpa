package dev.mbo.springkotlinjpa

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@Import(TestcontainersConfiguration::class)
@SpringBootTest
@ActiveProfiles(value = ["test"])
abstract class AbstractSpringBootTest {

    @Test
    protected fun checkLoading() {
        // do nothing
    }
}