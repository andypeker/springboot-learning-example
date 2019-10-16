package com.spring.springboot.autocfg;

import com.spring.springboot.service.Person;
import com.spring.springboot.service.PersonProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frankie Yang on 2019-10-15.
 */
@Configuration
@EnableConfigurationProperties(PersonProp.class)
@ConditionalOnClass(Person.class)
@ConditionalOnProperty(prefix = "yang", value = "wei", /*matchIfMissing = true*/havingValue = "defaultvalue")
public class PersonAutoConfiguration {
    @Autowired
    public PersonProp personProp;

    @Value("${yang.wei}")
    public String weiwei;

    @Bean
    @ConditionalOnMissingBean(Person.class)
    public Person person() {
//        return new Person();
        Person p = new Person();
        p.setName(personProp.getName());
        p.setAge(personProp.getAge());
        p.setGender(personProp.getGender());
        p.setId(personProp.getId());
        p.setIncome(personProp.getIncome());
//        p.setInfor(personProp.getInfor());
        p.setInfor(personProp.getInfor() + "?"+ weiwei);

        return p;
    }
}
