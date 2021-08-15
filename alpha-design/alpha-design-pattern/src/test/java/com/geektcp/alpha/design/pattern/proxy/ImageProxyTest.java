package com.geektcp.alpha.design.pattern.proxy;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

/**
 * @author tanghaiyang on 2019/9/21.
 */
public class ImageProxyTest {

    @Test
    public void main() throws Exception {
        String image = "http://image.jpg";
        URL url = new URL(image);
        HighResolutionImage highResolutionImage = new HighResolutionImage(url);
        ImageProxy imageProxy = new ImageProxy(highResolutionImage);
        imageProxy.showImage();
        Assert.assertTrue(true);
    }
}
