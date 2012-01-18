package com.netaporter.eventinator.domain;

import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.model.Customer;
import junit.framework.Assert;
import org.junit.Test;

import java.io.Serializable;

/**
 * User: gawain
 */
public class ObjectFieldRetrieverUnitTest {



    @Test
    public void test() {
         String id = "123";
         ObjectFieldRetriever<Serializable> objectFieldRetriever = new ObjectFieldRetriever("id");

        CustomerBuilder builder = CustomerBuilder.create("testname", "password", "test@test.com");
        builder.setId(id);
        Customer cust = builder.build();

        Serializable custId =  objectFieldRetriever.getFieldValue(cust);

        Assert.assertEquals(id, custId);

    }
}
