# NewsPortal

A Java Spark back end news Portal API.

## Getting Started

ensure to follow these instructions to set upa and run succefully. 

## Setup Instructions

* Ensure gradle is installed, synched and all dependencies donwloaded. 
* Run this command to clone into your local repo
```
git clone https://github.com/OderoOluoch/newsPortal.git

```

## Database set up Instructions



run
 
 ```
 $ psql < create.sql
 ```

if this doesn't work, go and do this manually. as below

```
CREATE DATABASE portal;

```

```
\c portal;

```

```
CREATE TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
name VARCHAR,
deptId int
);
```

```
CREATE TABLE IF NOT EXISTS departments (
id SERIAL PRIMARY KEY,
name VARCHAR
);
```

```
CREATE TABLE IF NOT EXISTS news (
id SERIAL PRIMARY KEY,
title VARCHAR,
content VARCHAR,
authorid INTEGER
);
```

```
CREATE TABLE IF NOT EXISTS news_depatments (
id SERIAL PRIMARY KEY,
newsid INTEGER,
departmentid INTEGER
);
```

```
CREATE DATABASE portal_test WITH TEMPLATE portal;
```



* Navigate to the root folder of the repo via your terminal. 
* Run this command to start gradle via the terminal 

```
$ gradle run

```

* Use this URL on your broswer `localhost:4567` , you will see 




 | URL end point | Description |
        |:---        |          
        |Departments|

        | ``/departmets/new``        | POST | Url to create a new department  |
         | ``/departmets``           | GET | Url to view a list of all departments  |
          | ``/departmets/id``       | GET | Url view a single department based on id |
           | ``/departmets/id/news`` | GET   | Url to view news items of a specifc department - ``ie General``  |
          
        |Users|

        | ``/users/new``   | POST | Url to create a new user  |
         | ``/users``      | GET  |Url to view a list of all users  |
          | ``/users/id``  | GET  |Url view a single user based on id |
          

        |News|

        | ``/news/new``                         | POST | Url to create a new department  |
         | ``/news``                            | GET  | Url to view a list of all news brifing  |
          | ``/news/id``                        | GET  | Url view a single news briefing based on id |
           | ``/news/id/departmets/deptid``     | POST | Url to add a news briefing to a specific department |
            | ``/news/id/departmets``           | GET  | Url to view all the departments that are associated with a news briefing item. 

            ---: |








A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
