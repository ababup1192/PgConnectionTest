package org.ababup1192;

import org.mockito.Mockito;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;

public class MyInitialContextFactory implements InitialContextFactory{
    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        return Mockito.mock(Context.class);
    }
}
