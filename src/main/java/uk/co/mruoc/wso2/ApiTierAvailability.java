package uk.co.mruoc.wso2;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public enum ApiTierAvailability {

    GOLD,
    SILVER,
    BRONZE,
    UNLIMITED;

    public static List<ApiTierAvailability> toTiersList(List<String> names) {
        List<ApiTierAvailability> tiers = new ArrayList<>();
        for (String name : names)
            tiers.add(parse(name));
        return tiers;
    }

    public static ApiTierAvailability parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

    public static List<String> toNames(List<ApiTierAvailability> values) {
        List<String> names = new ArrayList<>();
        for (ApiTierAvailability value : values)
            names.add(UrlEncoder.encode(WordUtils.capitalize(value.name().toLowerCase())));
        return names;
    }

}
