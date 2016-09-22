import com.tylersuehr.sqliteparser.SQLiteParser;
import java.util.UUID;

/**
 * Copyright Tyler Suehr 2016
 * Created by tyler
 */
public class Main {
    public static void main(String[] args) {
        SQLiteParser parser = SQLiteParser.getInstance();

        String table = parser.parse(Person.class);
        String table2 = parser.parse(Child.class);

        assert (table != null);
    }


    private static class Child {
        private UUID id;
        private String firstName;
        private String lastName;
        private String parentId;


        public Child() {}

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    private static class Person {
        private int id;
        private String firstName;
        private String lastName;


        private Person() {}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
}