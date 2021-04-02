package com.petkov.spr_final_1.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.petkov.spr_final_1.utils.ActiveTestTransporter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                create();
    }

    //todo - ActiveTestTransporter Bean - change the scope/make prototype?
    //todo - ActiveTestTransporter -  move somewhere else?
    @Bean
    public ActiveTestTransporter activeTestTransporter(){
        return new ActiveTestTransporter();
    }
}
