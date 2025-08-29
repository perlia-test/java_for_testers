package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

    @Entity
    @Table(name = "addressbook")
    public class ContactRecord {

        @Id
        @Column(name = "id")
        public int id;

        @Column(name = "firstname")
        public String firstname;

        @Column(name = "middlename")
        public String middlename;

        @Column(name = "lastname")
        public String lastname;

        @Column(name = "nickname")
        public String nickname;

        @Column(name = "company")
        public String company;

        @Column(name = "title")
        public String title;

        @Column(name = "address")
        public String address;

        @Column(name = "home")
        public String homePhone;

        @Column(name = "mobile")
        public String mobilePhone;

        @Column(name = "work")
        public String workPhone;

        @Column(name = "fax")
        public String fax;

        @Column(name = "email")
        public String email;

        @Column(name = "email2")
        public String email2;

        @Column(name = "email3")
        public String email3;

        @Column(name = "homepage")
        public String homepage;

        @Column(name = "bday")
        public String bday;

        @Column(name = "bmonth")
        public String bmonth;

        @Column(name = "byear")
        public String byear;

        @Column(name = "aday")
        public String aday;

        @Column(name = "amonth")
        public String amonth;

        @Column(name = "ayear")
        public String ayear;

    }
