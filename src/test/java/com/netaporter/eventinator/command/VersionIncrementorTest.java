package com.netaporter.eventinator.command;

import com.netaporter.eventinator.domain.VersionIncrementor;
import com.netaporter.eventinator.test.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.test.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: gawain
 */
public class VersionIncrementorTest {


    @Test
    public void testShouldIncrementCustomerVersionField() {

        VersionIncrementor incrementor = new VersionIncrementor();

        CustomerBuilder builder = CustomerBuilder.create("", "" ,"");
        Customer customer = new Customer(builder);

        Assert.assertEquals(0, customer.getVersion());

        incrementor.incrementVersion(customer);

         Assert.assertEquals(1, customer.getVersion());

        incrementor.incrementVersion(customer);

         Assert.assertEquals(2, customer.getVersion());
    }
}
