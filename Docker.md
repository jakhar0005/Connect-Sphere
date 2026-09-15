# install docker -> open powrshell run wsl --install
# then open the app docker will start running then run command = docker run nginx.
# if you want to interact with nginx the use = docker run -p 8090:80 nginx
# if you want to use docker here intellij
# export PATH="$PATH:/c/Users/Aman Jakhar/AppData/Local/Programs/DockerDesktop/resources/bin"
# above will look in that folder to run any commands you want

#                docker run nginx
#                │
#                ↓
#            Docker CLI receives command
#            │
#            ↓
#            Docker Engine
#            │
#            ↓
#        Check nginx image locally
#        /             \
#        exists          doesn't exist
#        │                 │
#        │                 ↓
#        │          Pull from Docker Hub
#        │                 │
#        └────────┬────────┘
#        ↓
#        Create container
#        │
#        ↓
#        Create isolated filesystem
#        │
#        ↓
#        Set up namespaces/network
#        │
#        ↓
#        Configure port mapping
#        8080 → 80
#        │
#        ↓
#        Start container process
#        │
#        ↓
#        nginx
#        │
#        ↓
#        nginx listens on :80
#        │
#        ↓
#        Container RUNNING

#create docker file using docker file
#go to the folder where you created docler file in command line
# run command docker build –t tagName(tag name ex = (write you image name you want)my-image:v1) . = Creates docker image
# run image = docker run my-image:v1

#create docker file using maven plugin
# make sure spring-boot-maven-plugin ispresesnt in pom.xml
# then run command: ./mvnw clean install spring-boot:build-image
# so when you do above a jar will be created in target folder ex = product-service-0.0.1-SNAPSHOT.jar
# then use command = docker tag product-service:0.0.1-SNAPSHOT Jakhar005/product-service
# then push image to docker hub = docker push Jakhar005/product-service:0.0.1-SNAPSHOT
# image created


# Push images to docker hub
#Step 1: Create an Account on Docker hub (hub.docker.com)
#Step 2: Run docker login on terminal and provide the username and password
#Step 3: Tag the image:    docker tag <image-name>:<version> <username>/<image-name>
#Step 4: Push the image: docker push <username>/<image-name>:<version>

#Using Docker compose
# As you have created docker compose file and docker file in each service
# Just run docker compose up to run it and docker compose down to stop it thats it.

# To do at end use 