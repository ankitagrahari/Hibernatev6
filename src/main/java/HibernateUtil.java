import entity.MetadataField;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    
    private static SessionFactory sessionFactory;
    private static Properties props;
    
    static {
        System.out.println("Obtaining SessionFactory object");
        if (null == sessionFactory) {
            Configuration cfg = new Configuration();
            cfg.addFile("src/main/resources/system.hbm.xml");
            
            try {
                FileInputStream fis = new FileInputStream("src/main/resources/hibernate.properties");
                props = new Properties();
                props.load(fis);
                fis.close();
                cfg.setProperties(props);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();
            sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        }
        System.out.println("Session Factory Object obtained:" + sessionFactory);
    }
    
    public static SessionFactory getSessionFactory() {
        System.out.println("Get existing SessionFactory object");
        return sessionFactory;
    }
    
    public static Session createSession() {
        System.out.println("Creating new session");
        return sessionFactory.openSession();
    }
    
    public static void closeSession(Session session) {
        if (null != session) {
            System.out.println("Close existing session");
            session.close();
        }
    }
    
    public static List query(String hql, Object... params) {
        Session session = null;
        try {
            session = createSession();
            Query query = session.createQuery(hql);
            
            for (int i = 0; i < params.length; i++) {
                // Must use setParameter(String, Object) instead of setParameter(int, Object)
                query.setParameter(i + 1, params[i]);
            }
            List<Object[]> found = query.list();
            return found;
        } catch (JDBCException e) {
            System.out.println("JDBCException executing query '" + hql + "'. Database may be down or unavailable.");
            e.printStackTrace();
        } catch (HibernateException e) {
            System.out.println("Hbernate exception executing query '" + hql + "'");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return Collections.emptyList();
    }
    
    public static <T extends Object> T save(T save) {
        Session session = null;
        Transaction txn = null;
        try {
            session = createSession();
            txn = session.beginTransaction();
            session.saveOrUpdate(save);
            txn.commit();
            
            return save;
            
        } catch (HibernateException e) {
            System.out.println("Hbernate exception executing save");
            e.printStackTrace();
        } finally {
            closeSession(session);
        }
        return null;
    }
    
    public static List<MetadataField> findMetadataFields(String name) {
        String hql = "from MetadataField f";// where f.ClassName=?1";
        return query(hql);
    }
}
