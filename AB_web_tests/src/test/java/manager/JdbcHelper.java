package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {
    public JdbcHelper (ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupsList(){
        var groups = new ArrayList<GroupData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
            {
                while (result.next()) {
                    groups.add(new GroupData()
                            .withId(result.getString("group_id"))
                            .withName(result.getString("group_name"))
                            .withHeader(result.getString("group_header"))
                            .withFooter(result.getString("group_footer")));
                }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<ContactData> getContactsList() {
        var contacts = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT id, firstname, middlename, lastname, nickname, company, title, " +
                             "address, home, mobile, work, fax, email, email2, email3, homepage, bday, bmonth, byear " +
                             "aday, amonth, ayear, photo FROM addressbook")) {
            while (result.next()) {
                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withMiddleName(result.getString("middlename"))
                        .withLastName(result.getString("lastname"))
                        .withNickname(result.getString("nickname"))
                        .withPhoto(result.getString("photo"))
                        .withTitle(result.getString("title"))
                        .withCompany(result.getString("company"))
                        .withAddress(result.getString("address"))
                        .withHomePhone(result.getString("home"))
                        .withMobilePhone(result.getString("mobile"))
                        .withWorkPhone(result.getString("work"))
                        .withFax(result.getString("fax"))
                        .withEmail(result.getString("email"))
                        .withEmail2(result.getString("email2"))
                        .withEmail3(result.getString("email3"))
                        .withHomePage(result.getString("homepage"))
                        .withBirthday(result.getString("bday"), result.getString("bmonth"), result.getString("byear"))
                        .withAnniversary(result.getInt("aday"), result.getString("amonth"), result.getString("ayear")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void checkConsistancy() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT * FROM address_in_groups ag JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL;"))
        {

            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int getAddressInGroupCount() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook","root","");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT COUNT(*) FROM `address_in_groups`;")) {
             var count = 0;
             while (result.next()) {
                count = result.getInt(1);
            }
            return count;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }


    }