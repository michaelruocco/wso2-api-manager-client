package uk.co.mruoc.wso2;

import org.apache.commons.lang3.StringUtils;

public class SequencesArgumentBuilder {

    public String build(AddApiParams params) {
        String result = buildInSequence(params);
        result += buildOutSequence(params);
        return result;
    }

    private String buildInSequence(AddApiParams params) {
        String inSequence = params.getInSequence();
        if (StringUtils.isEmpty(inSequence))
            return "";
        return "&inSequence=" + inSequence;
    }

    private String buildOutSequence(AddApiParams params) {
        String outSequence = params.getOutSequence();
        if (StringUtils.isEmpty(outSequence))
            return "";
        return "&outSequence=" + outSequence;
    }

}
