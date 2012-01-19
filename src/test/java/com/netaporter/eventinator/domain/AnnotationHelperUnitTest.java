package com.netaporter.eventinator.domain;

import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.model.Customer;
import junit.framework.Assert;
import org.junit.Test;

/**
 * User: gawain
 */
public class AnnotationHelperUnitTest {

    @Test
    public void test() {

        Integer version = 8;
        Customer cust = CustomerBuilder.create("name", "pass", "test@test.com").setId("123").setVersion(version).build();
        Object value = AnnotationHelper.getAnnotatedFieldValue(cust, DomainVersion.class);
        Assert.assertEquals(version, value);
    }
}
