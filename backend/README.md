# Backend

1. List all gradle tasks ./gradlew tasks --all
2. ./gradlew clean build docker generateDockerCompose
   1. Clean the previous build, 
   2. create a new build your application, 
   3. create docker image, 
   4. create docker-compose 
3. Start your docker-compose ./gradlew dockerComposeUp
4. Check if the image is running docker ps
5. Stop the containers ./gradlew dockerComposeDown