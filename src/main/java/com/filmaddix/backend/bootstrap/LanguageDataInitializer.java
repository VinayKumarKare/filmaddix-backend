package com.filmaddix.backend.bootstrap;

import com.filmaddix.backend.entity.Language;
import com.filmaddix.backend.repository.LanguageRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageDataInitializer implements ApplicationRunner {

    private final LanguageRepository languageRepository;

    public LanguageDataInitializer(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void run(ApplicationArguments args) {

        List<Language> masterLanguages = List.of(
                new Language("en", "English"),
                new Language("hi", "Hindi"),
                new Language("ta", "Tamil"),
                new Language("te", "Telugu"),
                new Language("ml", "Malayalam"),
                new Language("kn", "Kannada")
        );

        for (Language lang : masterLanguages) {
            if (!languageRepository.existsByCodeIgnoreCase(lang.getCode())) {
                languageRepository.save(lang);
            }
        }

        System.out.println("✅ Language master data ensured");
    }
}
