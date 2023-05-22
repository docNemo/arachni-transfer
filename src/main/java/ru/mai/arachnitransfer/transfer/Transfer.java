package ru.mai.arachnitransfer.transfer;

import lombok.RequiredArgsConstructor;
import ru.mai.arachnitransfer.reader.ArticleReader;
import ru.mai.arachnitransfer.reader.ArticleXml;
import ru.mai.arachnitransfer.writer.ArticleWriter;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Transfer {
    private static final int NUM_THREAD = 64;
    private static final String DIR = "articles";

    private final ArticleReader articleReader;
    private final ArticleWriter articleWriter;
    private final Queue<String> filePaths;

    public void transferSingle(String filePath) {
        ArticleXml articleXml = articleReader.readArticleFromXml(filePath);
        articleWriter.write(articleXml);
    }

    public void transferAll() {
        String filePath;
        while (!filePaths.isEmpty()) {
            synchronized (filePaths) {
                filePath = filePaths.poll();
            }
            transferSingle(filePath);
        }
    }

    public void start() {
        File dir = new File(DIR);
        filePaths.addAll(
                Arrays
                        .stream(
                                Objects.requireNonNull(dir.listFiles(name -> name.getName().endsWith(".xml")))
                        )
                        .map(File::getName)
                        .toList());


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(NUM_THREAD, NUM_THREAD,
                0, TimeUnit.SECONDS, new SynchronousQueue<>());
        threadPoolExecutor.close();
//        try (
//
//        ) {
//
//            Runnable task = this::transferAll;
//
//            for (int i = 0; i < NUM_THREAD; i++) {
//                threadPoolExecutor.submit(task);
//            }
//
//            threadPoolExecutor.shutdown();
//        }


    }
}
