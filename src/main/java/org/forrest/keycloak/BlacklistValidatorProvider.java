package org.forrest.keycloak;

import org.keycloak.provider.ConfiguredProvider;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.validate.AbstractStringValidator;
import org.keycloak.validate.ValidationContext;
import org.keycloak.validate.ValidationError;
import org.keycloak.validate.ValidatorConfig;


import java.util.Arrays;
import java.util.List;

public class BlacklistValidatorProvider extends AbstractStringValidator implements ConfiguredProvider {
    public static final String ID = "blacklist-validator";
    public static final String BLACKLIST = "list";
    public static final String ERROR_MESSAGE = "message";

    @Override
    public String getHelpText() {
        return "Use a comma-separated list, for example: foo,bar";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create().property()
                .name(BLACKLIST)
                .type(ProviderConfigProperty.STRING_TYPE)
                .label("Comma separated list")
                .defaultValue("")
                .required(true)
                .add()
                .property()
                .name(ERROR_MESSAGE)
                .type(ProviderConfigProperty.STRING_TYPE)
                .label("Error message")
                .defaultValue("This is an unacceptable value.")
                .required(false)
                .add().build();
    }

    @Override
    public <C> C getConfig() {
        return ConfiguredProvider.super.getConfig();
    }

    @Override
    protected void doValidate(String attributeValue, String attributeName, ValidationContext context, ValidatorConfig validatorConfig) {
        String[] blacklist = validatorConfig.getString(BLACKLIST).split(",", -1);
        if (blacklist.length > 0 && Arrays.asList(blacklist).contains(attributeValue)) {
            context.addError(new ValidationError(ID, attributeName, validatorConfig.getString(ERROR_MESSAGE), attributeValue));
        }
    }

    @Override
    public String getId() {
        return ID;
    }
}
