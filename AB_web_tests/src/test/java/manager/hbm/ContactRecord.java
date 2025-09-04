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
        public String first_name;

        @Column(name = "middlename")
        public String middle_name;

        @Column(name = "lastname")
        public String last_name;

        @Column(name = "nickname")
        public String nickname;

        @Column(name = "photo")
        private String photo;

        @Column(name = "company")
        public String company;

        @Column(name = "title")
        public String title;

        @Column(name = "address")
        public String address;

        @Column(name = "home")
        public String home_phone;

        @Column(name = "mobile")
        public String mobile_phone;

        @Column(name = "work")
        public String work_phone;

        @Column(name = "fax")
        public String fax;

        @Column(name = "email")
        public String email;

        @Column(name = "email2")
        public String email_2;

        @Column(name = "email3")
        public String email_3;

        @Column(name = "homepage")
        public String homepage;

        @Column(name = "bday")
        public String birthdayDay;

        @Column(name = "bmonth")
        public String birthdayMonth;

        @Column(name = "byear")
        public String birthdayYear;

        @Column(name = "aday")
        public Integer anniversaryDay;

        @Column(name = "amonth")
        public String anniversaryMonth;

        @Column(name = "ayear")
        public String anniversaryYear;

        public ContactRecord(){
        };
        public ContactRecord(int id,
                             String first_name,
                             String middle_name,
                             String last_name,
                             String nickname,
                             String photo,
                             String title,
                             String company,
                             String address,
                             String home_phone,
                             String mobile_phone,
                             String work_phone,
                             String fax,
                             String email,
                             String email_2,
                             String email_3,
                             String homepage,
                             String birthdayDay,
                             String birthdayMonth,
                             String birthdayYear,
                             Integer anniversaryDay,
                             String anniversaryMonth,
                             String anniversaryYear)

        {
            this.id = id;
            this.first_name = first_name;
            this.middle_name = middle_name;
            this.last_name = last_name;
            this.nickname = nickname;
            this.photo = photo;
            this.title = title;
            this.company = company;
            this.address = address;
            this.home_phone = home_phone;
            this.mobile_phone = mobile_phone;
            this.work_phone = work_phone;
            this.fax = fax;
            this.email = email;
            this.email_2 = email_2;
            this.email_3 = email_3;
            this.homepage = homepage;
            this.birthdayDay = birthdayDay;
            this.birthdayMonth = birthdayMonth;
            this.birthdayYear = birthdayYear;
            this.anniversaryDay = anniversaryDay;
            this.anniversaryMonth = anniversaryMonth;
            this.anniversaryYear = anniversaryYear;
        }

    }
