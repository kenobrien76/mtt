mtt
===

mtt todo assignment


get running
===
Build the app from parent folder 'todo'  using 'mvn clean install'
CD to todo-web and run 'mvn jetty:run'
Go to 'http://localhost:8080/todo'

User details
  --username:test
  --password:abc123


Structure
===
App has the following maven structure                                                                                  
todo(parent)
  todo-web(ui and REST service)
  todo-core(core services,domain model)
       
       
todo-core
===
Core application services,domain model and persistnce.
Here I made use of spring-data-jpa, this almost elimanted the need for any coding of a
persistence layer. I chose this library as this is a pretty basic CRUD application where no
complex db queries were required.

The rest of the core is just pretty standard spring bean's configuration coded to interfaces.
Testing, here I made heavy use of the mocikto library.

Database: H2 embedded database was used here. You wil notice a folder 'todo-db' created under todo-web
when you run the application, this is just the generated H2 file for application persistence.
If you install a h2 client , the h2 console 'http://www.h2database.com/html/main.html' you
can connect to the application database and add new users if required.
Connection details for H2 client:
jdbc : jdbc:h2:"add your full path here"/todo/todo-web/todo-db/todo-local;MODE=Oracle;IFEXISTS=TRUE;AUTO_SERVER=TRUE

todo-web
===
REST controller: These just provide the REST service around the core's exposed services. JSON is returned
by the controllers. Again all the controller are testing using mockito as the mock framework and the Spring MVC
test library to mock http methods.


Security is implemented using spring security.


UI
===
Twitter Bootstrap, for look and feel.
AngualJS, for front end logic, this allowed to to apply MVC pattern to the UI and give 
further speration between the styling and the logic to interact with the todo REST service.



If I had the time
===
 --Add registration page
 --Add search capabilities
 --End to End selenium acceptance tests 
 --Grunt to manage javascript build
 --Bower to manage javascrips dependencies
 --Jasmin to javascript tests

Some thoughts
===
Seeing as this was a basic CRUD app, technologies like Rails or Grails would be more productive. Or possible just nodeJS and a Mongo database.






  
  
