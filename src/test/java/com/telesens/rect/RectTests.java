package com.telesens.rect;

import com.acedemy.demo.Rect;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class RectTests {

    public void testPerimeter() {
        Rect rect = new Rect(2, 3);
        Assert.assertEquals(rect.perimeter(), 10);
    }

    public void testPSquare() {
        Rect rect = new Rect(2, 3);
        Assert.assertEquals(rect.square(), 6);
    }
}
