# Getting Started
In this branch of project _DaoAuthenticationProvide_ prvoides the way to authenticate customer using username and
 password. It is implemented by _UserDetailsService_ that gets the user details from the database.

## Installing
The guide of installing the project will be applied on ![Ubuntu 18.04](https://releases.ubuntu.com/18.04/)

## What you need
- [git 2.17.1+](https://git-scm.com/downloads)
- [Maven 3.6.0+](https://maven.apache.org/download.cgi)
- [JDK 1.8](https://www.oracle.com/java/technologies/downloads/) or later
- [MySQL 5.7+](https://www.mysql.com/downloads/)

## Prepare for work

### Set up Maven
At first you need create a _~/.m2_ directory
```
mkdir ~/.m2
```
Then create a _.m2/settings.xml_ file 
```
<?mxl version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
    https://maven.apache.org/xsd/settings-1.0.0.xsd">

<mirrors>
    <mirror>
        <id>centralmirror</id>
        <name>Apache maven central mirror Spain</name>
        <url>http://downloads.centralmirror.com/public/maven</url>
        <mirrorOf>maven_central</mirrorOf>
    </mirror>
</mirrors>

</settings>

```

For more details, see [that](https://www.baeldung.com/install-maven-on-windows-linux-mac)

### Set up MySQL
At first you need create a user to connect to the database. In my case the user's name
is _evgen_
```
mysql -u root -p 
> CREATE USER 'evgen'@'localhost' IDENTIFIED BY 'evgen';
> GRANT ALL PRIVILEGES ON *.* TO 'evgen'@'localhost';
> FLUSH PRIVILEGES;
```
For more details, see [that](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04)

After that you must run sql script in _/resources/db.sql_ and check the connection to database _webapp_

## Clone the project
```
git clone https://github.com/ZhekaPresnov/Spring-Security-Wep-Application
cd Spring-Security-Wep-Application
git checkout dev-role-based-authentication
```

## Running the tests
```
mvn test
```

## Build the project
```
mvn spring-boot:run
```
After that open the browser
```
http://localhost:8080/books/list
```

#### License
The source code is released under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0.html)
