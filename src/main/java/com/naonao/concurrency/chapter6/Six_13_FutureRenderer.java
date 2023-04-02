package com.naonao.concurrency.chapter6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Six_13_FutureRenderer {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };
        Future<List<ImageData>> submit = executor.submit(task);
        renderText(source);
        try {
            List<ImageData> imageData = submit.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            System.out.println("task interrupted");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("task execution failed");
            throw new RuntimeException(e);
        }

    }

    private void renderImage(ImageData imageData) {

    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

    interface ImageInfo {
        ImageData downloadImage();
    }

    interface Executor {
        void execute(Runnable r);
    }

    private class ImageData {
    }
}
