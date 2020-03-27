package com.nullexceptional.digibooky.domain.security.authentication.external;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class FakeAuthenticationService {
    private List<ExternalAuthentication> externalAuthentications = newArrayList(
            ExternalAuthentication.externalAuthentication().withInss("1234").withEmail("1234@gmail.com").withRoles(newArrayList("Admin"))
    );

    public Optional<ExternalAuthentication> getUser(String inss, String email) {
        return externalAuthentications.stream()
                .filter(externalAuthentication -> externalAuthentication.getInss().equals(inss))
                .filter(externalAuthentication -> externalAuthentication.getEmail().equals(email))
                .findFirst();
    }
}
