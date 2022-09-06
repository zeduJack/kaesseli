# Clean the old artifacts and build the application
./gradlew clean build

# Build docker container for X64 architecture
docker buildx build --platform linux/amd64 -t kaesseliacr.azurecr.io/kaesseli-api:latest .

# azure cli login
az login

# Run it once to get configuration for kubernetes cluster
# az aks get-credentials --resource-group rg-chote-001 --name kaesseli-cluster

# docker push image to azure
docker push kaesseliacr.azurecr.io/kaesseli-api:latest

# deploy all in azure
kubectl apply -f helm_chart.yaml