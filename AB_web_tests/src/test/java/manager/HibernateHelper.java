package manager;


import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.cfg.AvailableSettings.*;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper (ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                //.addAnnotatedClass(Book.class)
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(USER,"root")
                .setProperty(PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertGroupData(record));
        }
        return result;
    }

    private static GroupData convertGroupData(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header,record.footer);
    }

    private static GroupRecord convertGroupRecord(GroupData data) {
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(),data.footer());
    }


    public List<GroupData> getGroupList(){
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertGroupRecord(groupData));
            session.getTransaction().commit();
        });
    }
    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertContactData(record));
        }
        return result;
    }

    private static ContactData convertContactData(ContactRecord record) {
        return new ContactData(""+record.id, record.first_name, record.middle_name, record.last_name,
                record.nickname, "", record.company, record.title,
                record.address, record.home_phone, record.mobile_phone,
                record.work_phone, record.fax, record.email, record.email_2, record.email_3,
                record.homepage, record.birthdayDay, record.birthdayMonth, record.birthdayYear,
                record.anniversaryDay, record.anniversaryMonth, record.anniversaryYear);
    }

    private static ContactRecord convertContactRecord(ContactData data) {
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
            return new ContactRecord(Integer.parseInt(id), data.first_name(), data.middle_name(), data.last_name(),
                    data.nickname(), "", data.company(), data.title(),
                    data.address(), data.home_phone(), data.mobile_phone(),
                    data.work_phone(), data.fax(), data.email(), data.email_2(), data.email_3(),
                    data.homepage(), data.birthdayDay(), data.birthdayMonth(), data.birthdayYear(),
                    data.anniversaryDay(), data.anniversaryMonth(), data.anniversaryYear());
    }

    public List<ContactData> getContactList(){
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContactRecord(contactData));
            session.getTransaction().commit();
        });

    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
