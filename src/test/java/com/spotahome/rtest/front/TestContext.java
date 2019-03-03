package com.spotahome.rtest.front;

import com.spotahome.rtest.front.pageObjects.BasePageObject;

/**
 * Created by jiglesias on 12/02/17.
 */
public class TestContext<T extends BasePageObject> {
    private static final TestContext INSTANCE = new TestContext();

    private T pageObject;

    private TestContext() {

    }

    public static TestContext getInstance() {
        return INSTANCE;
    }

    public T setPageObject(T pageObject) {
        this.pageObject = pageObject;
        return pageObject;
    }

    public BasePageObject getPageObject() {
        return pageObject;
    }

    public T getPageObject(Class<T> type) {
        return pageObject;
    }
}
