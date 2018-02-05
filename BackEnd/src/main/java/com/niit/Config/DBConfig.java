package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.niit.Model.Blog;
import com.niit.Model.BlogComments;
import com.niit.Model.Events;
import com.niit.Model.Forum;
import com.niit.Model.ForumComments;
import com.niit.Model.ForumRequests;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.JobApplications;
import com.niit.Model.Notification;
import com.niit.Model.User;

@Configuration
@ComponentScan("com.niit")
public class DBConfig 
{
	@Autowired
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder  = new LocalSessionFactoryBuilder((javax.sql.DataSource) dataSource);
        /*sessionBuilder.setProperty("hibernate.show_sql", "true");*/
        
        sessionBuilder.addProperties(getHibernateProperties());
        
        sessionBuilder.addAnnotatedClass(User.class);
        sessionBuilder.addAnnotatedClass(Blog.class);
        sessionBuilder.addAnnotatedClass(Job.class);
        sessionBuilder.addAnnotatedClass(JobApplications.class);
        sessionBuilder.addAnnotatedClass(BlogComments.class);
        sessionBuilder.addAnnotatedClass(Forum.class);
        sessionBuilder.addAnnotatedClass(ForumComments.class);
        sessionBuilder.addAnnotatedClass(ForumRequests.class);
        sessionBuilder.addAnnotatedClass(Friend.class);
        sessionBuilder.addAnnotatedClass(Notification.class);
        
         
        
        
        
        return sessionBuilder.buildSessionFactory();
    }
    @Autowired
    @Bean(name = "datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/Collaboration2");

        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
  /*      properties.put("hibernate.connection.autocommit", true);*/
        return properties;
    }
    @Bean
	@Autowired
        public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
                return new HibernateTransactionManager(sessionFactory);
        }


}
