package test;

import static config.Credentials.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class OwnerLibraryTests {

    @Test
    @Tag("owner")
    void readFromProperties(){
        String login = credentials.login();
        String password = credentials.password();
        System.out.println(String.format("I try to sign in with login: '%s' and password: '%s' and city: %s", login, password, credentials.town()));
    }
}
