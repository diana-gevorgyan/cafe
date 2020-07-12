# Introduction
Cafe management web-application. Will Help you to manage your cafe easily!
 
# Requirements
* Java 11
* Maven(3.6.0 or higher)
* Node(v10.21.0)
* npm(v6.14.4)
* Docker & Docker compose

# Installation
* Clone this project to your local machine.

If you are using Linux:
* Run `build.sh` script to compile sources, build project, generate docker images.
* Run `run.sh` script to run all services using docker-compose.
* If you want to stop project please run `stop.sh`, this will stop docker-compose.

If you are using Windows:
* Run `build.bat` script to compile sources, build project, generate docker images.
* Run `run.bat` script to run all services using docker-compose.
* If you want to stop project please run `stop.bat`, this will stop docker-compose.

Then: 
* Open  `http://localhost:3000/` in your browser and use default user credentials to login.

```
Manager User
email:    kim_junior@northkorea.com
password: kim
```

# Notes
Project consists of two modules: `cafe-backend` and `cafe-frontend`. For more details please visit module specific README.md files.  
As project database is used PostgreSQL. All tables in the database are prefixed with `c_`,
because the names of some tables are reserved in Postgres.

Service Layer is covered by unit tests.

The application deployment consist of 3 services: cafe-backend, cafe-frontend, cafe-postgres.
Port 3000 is exposed for cafe-frontend and 8080 - for cafe-backend. As long as cafe-postgres is in the same network with cafe-backend, 
there's no need to expose it's port. You can have a look on docker-compose.yml for more details.
