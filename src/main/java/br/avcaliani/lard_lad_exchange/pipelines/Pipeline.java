package br.avcaliani.lard_lad_exchange.pipelines;

import br.avcaliani.lard_lad_exchange.cli.Args;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Random;

public abstract class Pipeline implements Serializable {

    private static final String SEP = "\n-------------------------------------\n";
    final Logger log;

    public Pipeline() {
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    public Pipeline init(String pipelineName) {
        log.info("{} Starting Pipeline: '{}' {} {}", SEP, pipelineName, emoji(), SEP);
        return this;
    }

    /**
     * Main pipeline method.
     * Here you should implement all the logic you need.
     *
     * @param args App arguments.
     * @return Itself.
     * @throws Exception Something not expected can happen ¯\_(ツ)_/¯
     */
    public abstract Pipeline run(Args args) throws Exception;

    public void sunset(String pipelineName) {
        log.info("{} Pipeline Finished: '{}' 🌇 {}", SEP, pipelineName, SEP);
    }

    private String emoji() {
        String[] emojis = {"🪴", "🌵", "🌻", "🌷", "🍀", "🍂", "🍁", "🌱"};
        return emojis[new Random().nextInt(emojis.length)];
    }
}
