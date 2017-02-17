package uk.co.mruoc.wso2;

public class SequencesArgumentBuilder {

    public String build(SequenceParams params) {
        String result = buildInSequence(params);
        result += buildOutSequence(params);
        return result;
    }

    private String buildInSequence(SequenceParams params) {
        String inSequence = params.getInSequence();
        return "inSequence=" + UrlEncoder.encode(inSequence);
    }

    private String buildOutSequence(SequenceParams params) {
        String outSequence = params.getOutSequence();
        return "&outSequence=" + UrlEncoder.encode(outSequence);
    }

}
