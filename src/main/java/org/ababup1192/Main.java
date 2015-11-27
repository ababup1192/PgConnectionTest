package org.ababup1192;

import org.postgresql.ds.PGPoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Main {

    public Main() {
        try {
            // JNDIのFactoryをjettyさんから借りる。
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.eclipse.jetty.jndi.InitialContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.eclipse.jetty.jndi");

            String dataSourceName = "java:/comp/env/jdbc/postgres";

            PGPoolingDataSource poolingDataSource = new PGPoolingDataSource();
            poolingDataSource.setDataSourceName(dataSourceName);
            poolingDataSource.setServerName("localhost");
            poolingDataSource.setPortNumber(5432);
            poolingDataSource.setDatabaseName("vagrant");
            poolingDataSource.setUser("vagrant");
            poolingDataSource.setPassword("vagrant");

            // dsを保存するためのディレクトリを作っていく。
            InitialContext context = new InitialContext();
            context.createSubcontext("jdbc");
            context.createSubcontext("jdbc/postgres");
            // ds1という名前でファイル(オブジェクト)を格納。
            context.rebind("jdbc/postgres/ds1", poolingDataSource);

            // 格納した名前で取り出してみる。
            PGPoolingDataSource dataSource = InitialContext.doLookup("jdbc/postgres/ds1");
            System.out.println(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        new Main();
    }
}
