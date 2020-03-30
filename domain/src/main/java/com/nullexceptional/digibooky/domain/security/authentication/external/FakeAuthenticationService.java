package com.nullexceptional.digibooky.domain.security.authentication.external;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class FakeAuthenticationService {
    private List<ExternalAuthentication> externalAuthentications = newArrayList(
            ExternalAuthentication.externalAuthentication().withInss("admin").withEmail("admin@gmail.com").withRoles(newArrayList("Admin")),
            ExternalAuthentication.externalAuthentication().withInss("member").withEmail("member@gmail.com").withRoles(newArrayList("Member")),
            ExternalAuthentication.externalAuthentication().withInss("librarian").withEmail("librarian@gmail.com").withRoles(newArrayList("Librarian"))

    );

    public Optional<ExternalAuthentication> getUser(String inss, String email) {
        return externalAuthentications.stream()
                .filter(externalAuthentication -> externalAuthentication.getInss().equals(inss))
                .filter(externalAuthentication -> externalAuthentication.getEmail().equals(email))
                .findFirst();
    }
}
