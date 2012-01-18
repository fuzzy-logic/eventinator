package com.netaporter.eventinator.command;

import com.netaporter.customerservice.customer.Customer;
import com.netaporter.customerservice.customer.builder.CustomerBuilder;
import com.netaporter.eventinator.command.VersionIncrementor;
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
