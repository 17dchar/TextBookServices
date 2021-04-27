# TextBookServices
## Table of contents
* [General Info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)


## General Info
testing
This project is designed to migrate the TextBook Services Application from WebFocus Maintain web application to Spring Boot Web Application.

## Technologies
Project is created with:
* Java version: 15
* Oracle Database version: 19c

## Setup
To run this project, 
* Clone the project to local machine.
* Use Java IDE (IntelliJ or Eclipse or STS)
* Under Project Configuration add ``` spring-boot:run``` 
## For Eclipse: 
 ![Ecplise Setup](https://i.stack.imgur.com/46ip8.png)
 ## For IntelliJ:
 ![IntelliJ Setup](https://i.ytimg.com/vi/ODwCh9THl8A/hqdefault.jpg)

* **Once the application configuration is done. You can access application in** 
```http://localhost:8080/```

## From the students to the developers!

###Things that need to be replaced
There's a couple of things that should be replaced within
this project, as should be expected. 

The main one is the method
of security we used for the project. Obviously we couldn't have
students be programming the security for the system they would
later be using. One, we're students, and the odds of
our security having flaws that would be deemed unacceptable were
absurdly high. Two, we're students (again), and although this
project was related to a class, our integrity to not put in
any faulty code on purpose hasn't been proven to the university 
(that's a main point of an interview, right there). We have implemented
a pseudo security system that has been commented whenever it shows
within the code. It should be fairly easy to remove/ replace.

The second one is the connection to the database. This one should only
be a change in the credentials given in the "applications.properties"
file found within "src/main/resources".


###AJAX Functions in HomeController
From the minimal amount of knowledge we have about Spring Boot, it is often for POST methods to
return a String that brings up the proper .jsp file, and whenever there was information to put
onto the screen, a ModelMap would be used to "put" the information on the view. For AJAX, however,
we never end up leaving the original page, but rather call very similar methods that actually
call information in form of JSON files that some javascript on each page would put in
the proper spot once AJAX successfully ran on each page. If a change back to the original
way is wanted, it shouldn't be very hard to make that change. We tried to keep everything as
close to how it would be as if we never had AJAX in case this change is wanted, just in case.
To make this change, at the beginning of each POST method, we would have to change the return
type to String, all return statements to the proper .jsp file, change all setters of a class
in the home controller to a ModelMap "put" function, and finally to make sure the .jsp files
pull this information with the proper "${**variable here**}". If we had more time, we planned
on making a commented out section for each section to make it easy to switch back and forth
between AJAX and non-AJAX, but we unfortunately didn't get that far. Sorry!

###Data Validation Concerns
A main comment of concern is how we went about the project compared
to how usual spring boot projects seem to go. We opted to use AJAX fairly
heavily within this project. The main reason is because this allows for
us to update pages without having to hit enter, hit a button, or anything.
This led to problems using javax validation heavily. We still used javax
validation, but in a less strict way. Usually if an error was caught within
a data validation check (seen as "if" statements holding "bindingResult.hasErrors()"
for this project), we could just return the url from there and spring will
automatically send errors to the .jsp file.

Unfortunately, with how we went with this project, that's not how it works exactly.
For our project, it will still check for data validation comparing to models (as
per usual), but if there are errors detected, it will go through and check only 
pieces of data that were relevent to the query. This is because for AJAX to work,
we needed models for the JSON information passed to be put into, which we just used
the same models that were made in the process of being able to query with the database.
We left comments where we do all of our data validation in case changes are wanted.

###AJAX Models to Return Data
Another potential concern would be how we returned data to the view. We used two main models
to do this: "OutputBookInformationModel" and "OutputStudentInformationModel". These two
models were meant for sending information that was requested by the user, either about
a book or about a student, respectively. Unfortunately, due to only using two models
for all potential posts, the JSONs spawned often have multiple null variables that aren't used
by certain pages. If we had more time for this project, we would have created a model for
each page to negate this problem.

##Small Side Notes
We have even more small things we changed that might not be wanted. For instance, every time
we have a situation where we call to the repository, we tried to keep the calls to a minimum.
Thus, whenever we called to the repository, and wanted to make a check with the repository,
we would make an item to hold the information, then check what was in that item. This was
our method to make sure we make as little of calls possible, but that could easily be wrong.