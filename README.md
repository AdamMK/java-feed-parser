# Java feed for SBG Feedme tech test

This is a poor attempt of having a go at SBG feed me test. Application is consuming the date from TCP feed and transform it to specific format as well as save it to the DB. There are 3 tasks within the project (my implementation is at the 2nd-ish stage). I will be going back to it and add a few bits and bobs to make it nice with time.

### Technologies used
* Gradle
* Java 11 with SpringBoot
* MongoDB

### Feed for the data can be found in docker repository
* https://hub.docker.com/r/sbgfeedme/provider/

### The task is decribed here
* https://github.com/sr1977/sbg-feedme-arrow

### Running the project

* Prerequisites
  * Docker
  * MongoDB
  * Gradle  
* download the feed from docker and run with `docker-compose up`
* clone the project and navigate to the project directory and run
    * `gradle build`
    * `gradle bootRun`
    


