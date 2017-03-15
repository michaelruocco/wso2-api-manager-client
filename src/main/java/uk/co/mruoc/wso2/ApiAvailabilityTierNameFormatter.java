package uk.co.mruoc.wso2;

import org.apache.commons.lang3.text.WordUtils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class ApiAvailabilityTierNameFormatter {

    public static String formatName(ApiTierAvailability tier) {
        if (tier == null)
            return EMPTY;
        return WordUtils.capitalize(tier.name().toLowerCase());
    }

}
