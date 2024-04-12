package com.grocery.demo;

import org.hibernate.dialect.MySQL8Dialect;

public class CustomMYSQLDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=MyISAM";
    }
}

