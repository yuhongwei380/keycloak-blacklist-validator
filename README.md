# Keycloak Blacklist Validator

How to use?
Usage Steps:

1.Compile from source using mvn.

2.Copy blacklist-validator-jar-with-dependencies.jar to Keycloak's providers directory.

3.Restart Keycloak.

4.Go to the Keycloak Console → Realm Settings → User Profile, click on username, then click Add Validator, and select blacklist-validator for configuration.
