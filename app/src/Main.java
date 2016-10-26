import com.tylersuehr.sqliteparser.IGNORE;
import com.tylersuehr.sqliteparser.FOREIGNKEY;
import com.tylersuehr.sqliteparser.SQLiteParser;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class Main {
    public static void main(String[] args) {
        SQLiteParser parser = SQLiteParser.getInstance();

        String addressTable = parser.parseTable(Address.class);
        String userTable = parser.parseTable(User.class);

        System.out.println(addressTable);
        System.out.println(userTable);
    }


    private static class Address {
        private int id;
        private String street;


        public Address() {}
    }

    private static class User {
        private int id;
        private String name;
        private String email;
        private String username;
        private String password;

        @FOREIGNKEY(fkDataType = int.class)
        private Address address;

        @IGNORE
        private long indexTime;


        public User() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}