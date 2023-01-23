
## AZURE CLOUD DEPLOYMENT

### Install Azure CLI

```bash
brew update && brew install azure-cli
```

## SETUP
We have currently a permission problem that needs to be addressed before performing the setup.

```bash
az login
```

### create Resource group
```bash
az group create --name example-resource-group --location switzerlandnorth
```

### create a container registry, which contains your image
```bash
az acr create -n exampleacr -g example-resource-group --sku basic
```
 
### create an AKS Kubernetes Service, where your application runs.
  Make sure there is permission to pull images from Container Registry into the AKS
  ```bash
  az aks create -g example-resource-group -n example-cluster --attach-acr exampleacr --enable-managed-identity --node-count 1
  ```

### Get credentials to manage your cluster from kubectl
```bash
az aks get-credentials -g example-resource-group -n example-cluster
```

Now that you have a Resource group and inside it a container registry and kubernetes service, where the application can run.
The above steps should be done once to create the resources and should not be repeated always.
During development phase make sure to stop your Kubernetes cluster to avoid extra costs.

```bash
az aks stop -g example-resource-group -n example-cluster
```

## DEPLOYMENT STEPS

### Clean the old artificats and build the application
```bash
./gradlew clean build
```

### Build docker container for X64 architecture
```bash
docker buildx build --platform linux/amd64 -t kaesseliacr.azurecr.io/kaesseli-api:latest .
```

### azure cli login
```bash
az login
```

```bash
az aks get-credentials --resource-group rg-chote-001 --name kaesseli-cluster
```

### docker push image to azure
```bash
docker push kaesseliacr.azurecr.io/kaesseli-api:latest
```

### deploy all in azure
```bash
kubectl apply -f helm_chart.yaml
```



## Setup Firebase account
Follow the guidelines in https://firebase.google.com/docs/admin/setup/#java_1

### Links
- https://learn.microsoft.com/en-us/azure/azure-resource-manager/management/manage-resource-groups-cli
- https://learn.microsoft.com/en-us/cli/azure/aks?view=azure-cli-latest
- https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/deploy-spring-boot-java-app-on-kubernetes
- https://learn.microsoft.com/en-us/azure/aks/learn/quick-kubernetes-deploy-portal?tabs=azure-cli



### Local Backend & DB (docker)

1. Build backend via gradle (gradle window): backend/Tasks/build -> right click -> run
2. List all gradle tasks: ./gradlew tasks --all
3. ./gradlew clean build docker generateDockerCompose
   1. Clean the previous build,
   2. create a new build your application,
   3. create docker image,
   4. create docker-compose
4. Create docker DB: docker compose up postgres
5. Check if the image is running docker ps
6. Stop the containers ./gradlew dockerComposeDown
7. Set "SPRING_PROFILES_ACTIVE=dev" in 
8. Start backend via gradle (gradle window): backend/Tasks/application/bootRun -> right click -> Debug / Run

## Setup Firebase account
For Unix and Mac:  
export GOOGLE_APPLICATION_CREDENTIALS="/path/to/kaesseli-18cf8-firebase-adminsdk-6uil0-12fca9f9e3.json"  

To run the test cases, place the service account json file inside this folder using the following name:  
your project folder/backend/secrets/kaesseli-firebase-adminsdk.json 

See also the guidelines in https://firebase.google.com/docs/admin/setup/#java_1