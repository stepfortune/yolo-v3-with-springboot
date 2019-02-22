package com.embed.detection.util;

import com.embed.detection.model.Box;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class DetectUtil {

    private boolean initOrNot = false;

    private long networkPointer = 0;

    @Value("${darknet.libPath}")
    private String libPath;

    @Value("${darknet.datacfgPath}")
    private String datacfgPath;

    @Value("${darknet.cfgPath}")
    private String cfgPath;

    @Value("${darknet.weightsPath}")
    private String weightsPath;

    private Logger logger = LoggerFactory.getLogger(DetectUtil.class);


    public native String detect(long networkPointer, String datacfgPath, String imgPath, String outPath);

    public native long initResource(String cfgPath, String weightsPath);

    public native void freeResource(long networkPointer);


    @PostConstruct
    private void init() {
        if (!isInitOrNot()) {
            System.load(libPath);
            networkPointer = initResource(cfgPath, weightsPath);
            setInitOrNot(true);
        }
    }

    public void free() {
        if (isInitOrNot()) {
            freeResource(networkPointer);
            networkPointer = 0;
            setInitOrNot(false);
        }
    }

    public synchronized List<Box> getDetectedBoxes(String imgPath, String outPath) {
        String[] items = detect(networkPointer, datacfgPath, imgPath, outPath).split("\n");
        if (items.length == 0) return null;
        List<Box> boxes = new ArrayList<>();
        for(String item : items) {
            String[] properties = item.split(" ");
            Box box = new Box(properties[0],
                    Float.valueOf(properties[1]),
                    Float.valueOf(properties[2]),
                    Float.valueOf(properties[3]),
                    Float.valueOf(properties[4]),
                    Float.valueOf(properties[5]));
            boxes.add(box);
        }
        if(!boxes.isEmpty()) return boxes;
        return null;
    }

    public synchronized String getDetectionResult(String imgPath, String outPath) {
        return detect(networkPointer, datacfgPath, imgPath, outPath);
    }

    public synchronized boolean isInitOrNot() {
        return initOrNot;
    }

    public synchronized void setInitOrNot(boolean initOrNot) {
        this.initOrNot = initOrNot;
    }
}
