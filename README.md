# SQLite Class Parser
### Summary
No more need to write your own SQLite queries and statements! You can automatically 
create an SQLite query or statement simply by making a Java class. This parser will
read the fields in a Java class and create SQLite queries or statements based off of
it. 

You can use annotations to denote constraints. *i.e.* FOREIGN KEY, NOT NULL, UNIQUE.
An annotation called IGNORE is included if you want a field that shouldn't be in a table.

There are also various options for the parser too; *i.e.* you can pluralize table names
and capitalize column names.

##### Full usage of SQLiteParser
```java
// Initialization
SQLiteParser parser = SQLiteParser.getInstance();

// Options
parser.setTablePluralize(true);
parser.setTableLowercase(true);
parser.setFilter(true);
parser.setColFirstUppercase(false);

// Parsing
String sqlTable = parser.parseTable(AnyKindOfObject.class);
```

### Example Code Simple
#### Create Java Class
```java
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    
    
    public User() {}
    
    //...
}
```

#### Using the SQLiteParser
```java
public class Main {
    public static void main(String[] args) {
        // Get instance of SQLiteParser
        final SQLiteParser parser = SQLiteParser.getInstance();
        
        // Get table creation statement for User class
        String userTable = parser.parseTable(User.class);
        
        
        // This will print out the following SQLite statement:
        // CREATE TABLE [users] ([id] INTEGER PRIMARY KEY, [firstName] TEXT, [lastName] TEXT, [username] TEXT);
        
        System.out.println(userTable);
    }
}
```

### Example Code Advanced (Foreign Keys & Annotations)
#### Create Java Classes
```java
public class Address {
    private int id;
    private String streetName;
    
    
    public Address() {}
    
    //...
}

public class User {
    private int id;
    private String firstName;
    
    FOREIGNKEY(fkDataType = int.class)
    private Address address;
    
    IGNORE
    private String lastname;
    
    
    public User() {}
    
    //...
}
```

#### Using the SQLiteParser
```java
public class Main {
    public static void main(String[] args) {
        // Get instance of SQLiteParser
        final SQLiteParser parser = SQLiteParser.getInstance();
        
        // Create SQLite table creation statement for Address model
        String addressTable = parser.parseTable(Address.class);
        
        // Create SQLite table creation statement for User model
        String userTable = parser.parseTable(User.class);
        
        // This will print out the following SQLite statements:
        // CREATE TABLE [addresses] ([id] INTEGER PRIMARY KEY, [streetName] TEXT);
        // CREATE TABLE [users] ([id] INTEGER PRIMARY KEY, [firstName] TEXT, [addressId] INTEGER, FOREIGN KEY ([addressId]) REFERENCES [addresses]([id]));
        
        System.out.println(addressTable);
        Syste.out.println(userTable);
    }
}
```

### Overview of SQLiteParser Design
![alt text](https://github.com/tylersuehr7/sqlite-class-parser/img_class_diagram.png "SQLiteParser Class Diagram")