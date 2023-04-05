# student
JDK Version 17

1 - Clone the code using 'https://github.com/Irshad046/student.git'

2 - Once cloned, it can be up and running if JDK 17 is connfigured sicne Mongo image is used in this repo


Dokcer Setup

1 - Image for the above can be pulled from 'https://hub.docker.com/layers/irshadahmad046/student/tagname/images/sha256:4ea82d0496ee1b5431352b4c1df5a382f265beb4e8c54c399034c4cad2d90a07'

2 - Once pulled on local machine, please use the 'docker-compose.yaml' file in the source code to start the application container using 'docker compose up -d'

3 - Please make sure the terminal is pointing to this 'docker-composse.yaml' file.

4 - Once it is up, please find the below urls for application.


Please be noted that in order to create student, we first need to create a department.

POST

http://localhost:8080/v1/department/add-department

Request Body
{
    "departmentName":"CS"
}


Once created, we can create new student for those department(s)

POST

http://localhost:8080/v1/student/add-student
{
    "firstName":"Some",
    "lastName":"Student",
    "department":"CS"
}


GET

http://localhost:8080/v1/student/get-all-students

GET - with Path variable

http://localhost:8080/v1/student/c2e6fbef-7bac-4b8c-995f-3fa24ad2413a


*** the implementation of filters was not clear since there was no clear requirement for that, however the filter is added in the application and it intercepts and checks for HTTP methods, if it is GET then there is a condition for it but it does nothing as of now due to unclear requirements.

*** the storage for now is temporary, meaning if the container is down, the data in it will also be lost. It can be saved my mountinng it to the machine on which is it running on using volumne in docker-compose but it not used for the sake of simplicity as of now.
