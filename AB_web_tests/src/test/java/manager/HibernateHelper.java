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
                .setProperty(URL, "jdbc:mysql://localhost/addressbook")
                .setProperty(USER,"root")
                .setProperty(PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        List<GroupData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertGroup(record));
        }
        return result;
    }
    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records){
            result.add(convertContact(record));
        }
        return result;
    }

    private static GroupData convertGroup(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header,record.footer);
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData(""+record.id, record.firstname, record.middlename, record.lastname,
                record.nickname, "", record.company, record.title,
                record.address, record.homePhone, record.mobilePhone,
                record.workPhone, record.fax, record.email, record.email2, record.email3,
                record.homepage, record.bday, record.bmonth, record.byear,
                record.aday, record.amonth, record.ayear);
    }

    public List<GroupData> getGroupList(){
        return convertGroupList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<ContactData> getContactList(){
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }
}
