package uk.co.mruoc.wso2;

import java.util.ArrayList;
import java.util.List;

public enum ApiTierAvailability {

    GOLD,
    SILVER,
    BRONZE,
    UNLIMITED;

    public static List<ApiTierAvailability> toTiersList(List<String> names) {
        List<ApiTierAvailability> tiers = new ArrayList<>();
        names.forEach(n -> tiers.add(parse(n)));
        return tiers;
    }

    public static ApiTierAvailability parse(String name) {
        return valueOf(name.toUpperCase().trim());
    }

}
