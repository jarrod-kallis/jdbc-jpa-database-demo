# jdbc-jpa-database-demo

[https://github.com/in28minutes/jpa-with-hibernate](https://github.com/in28minutes/jpa-with-hibernate) 

## JdbcTemplate Magic - How does it connect to H2 DB
1. In <b>application.properties</b> add `logging.level.root=debug`<br/>
2. After the starting application search for `CONDITIONS EVALUATION REPORT` in the console<br/>
3. Spring automatically configures a connection to H2 if it sees it in the dependencies/classpath<br/>
4. In the console you will see stuff like:<br/>
`DataSourceAutoConfiguration, DataSourceTransactionManagerAutoConfiguration, H2ConsoleAutoConfiguration, JdbcTemplateAutoConfiguration, dbcTemplateAutoConfiguration.JdbcTemplateConfiguration#jdbcTemplate`   

