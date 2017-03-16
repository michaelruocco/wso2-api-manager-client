package uk.co.mruoc.wso2.publisher;

import uk.co.mruoc.wso2.StringArgumentBuilder;

public class SequencesArgumentBuilder {

    private StringArgumentBuilder inSequenceArgumentBuilder = new StringArgumentBuilder("inSequence");
    private StringArgumentBuilder outSequenceArgumentBuilder = new StringArgumentBuilder("outSequence");

    public String build(SequenceParams params) {
        String result = buildInSequence(params);
        result += buildOutSequence(params);
        return result;
    }

    private String buildInSequence(SequenceParams params) {
        return inSequenceArgumentBuilder.build(params.getInSequence());
    }

    private String buildOutSequence(SequenceParams params) {
        return outSequenceArgumentBuilder.build(params.getOutSequence());
    }

}
