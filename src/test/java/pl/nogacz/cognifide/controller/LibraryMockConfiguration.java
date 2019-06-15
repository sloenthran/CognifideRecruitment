package pl.nogacz.cognifide.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import pl.nogacz.cognifide.library.Library;

/**
 * @author Dawid Nogacz on 15.06.2019
 */
@Configuration
@Profile("LibraryMock")
public class LibraryMockConfiguration {

    @Primary
    @Bean
    public Library libraryMock() {
        return Mockito.mock(Library.class);
    }
}