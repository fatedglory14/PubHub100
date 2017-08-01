# PubHub100

About:

This project is for learning basic object oriented programming and how it flows from backend to frontend.  To start out the project is already created as such: http://showcase.revature.com/PubHub100/

The goal of the project is to set it up locally on your system using Eclipse IDE and pgadmin to manage your postgres database that keeps the published book information in a table called books.

What I did:

After getting the programs installed locally and having everything set up, the first step is to create a new table using a .sql file called book_tags; this was my first dive into learning primary key, foreign key, functionality and using pgadmin as a way to keep up with what is happening within a database.

Next, the goal was to create some java classes;
I created a booktag model and a booktag DAO that would later be implemented with booktagDAOIMPL. 

After getting the objects set up and the particular methods that would be used the next goal was to create some servlets to manage the information from the frontend to the backend;
I created an AddTag, RemoveTag, and SearchByTag servlet to enhance the functionality of the PubHub publishing site.

With the servlets created I edited the header to add a link to searching by tag and also added add tag and remove tag functionality to the view book details jsp pages.  This required me to use knowledge of CSS, HTML5, and java to complete.

After about 30 days, the project was completed and the tagging system is active.

Some Information:

Object Oriented Program proved to be a bit cumbersome at first, but after a short while of working with it, it was nice to understand the model to method and implementation of said method's.  I had a lot of fun learning by trial and error with the getter and setter methods as well as the doGet and doPost methods.

After feeling comfortable with my results on the Revature PubHub100, I am now starting the PubHub200 project that requires creating a marketplace.
