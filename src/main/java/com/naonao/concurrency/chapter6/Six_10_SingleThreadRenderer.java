package com.naonao.concurrency.chapter6;

import java.util.ArrayList;
import java.util.List;

public class Six_10_SingleThreadRenderer {

        private final Executor executor = new Executor() {
            @Override
            public void execute(Runnable r) {
                r.run();
            }
        };

        void renderPage(CharSequence source) {
            renderText(source);
            List<ImageData> info = new ArrayList<>();
            for (ImageInfo imageInfo : scanForImageInfo(source)) {
                info.add(imageInfo.downloadImage());
            }
            for (ImageData imageData : info) {
                renderImage(imageData);
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
