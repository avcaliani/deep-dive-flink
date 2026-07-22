package br.avcaliani.lard_lad_exchange;

import br.avcaliani.lard_lad_exchange.cli.ArgParser;
import br.avcaliani.lard_lad_exchange.pipelines.Dummy;
import br.avcaliani.lard_lad_exchange.pipelines.ValidateTransactions;

public class App {

    public static void main(String[] arguments) throws Exception {

        var args = ArgParser.parse(arguments);
        var pipelineName = args.getPipeline();

        var pipeline = switch (pipelineName) {
            case "dummy" -> new Dummy();
            case "validate-transactions" -> new ValidateTransactions();
            default -> throw new RuntimeException("Pipeline doesn't exist! Name: " + pipelineName);
        };
        pipeline
            .init(pipelineName)
            .run(args)
            .sunset(pipelineName);
    }
}
