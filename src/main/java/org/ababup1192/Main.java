package org.ababup1192;

import org.postgresql.ds.PGPoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public Main() {
        try {
            // Naming(JNDI)を管理するためのファクトリをMockで用意する。
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.ababup1192.MyInitialContextFactory");

            PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();
            poolingDataSource.setDataSourceName("java:comp/env/jdbc/database");
            poolingDataSource.setServerName("localhost");
            poolingDataSource.setPortNumber(5432);
            poolingDataSource.setDatabaseName("vagrant");
            poolingDataSource.setUser("vagrant");
            poolingDataSource.setPassword("vagrant");
            new InitialContext().rebind("java:comp/env/jdbc/database", poolingDataSource);

            System.out.println("ぬけた！");
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        new Main();
    }
}
