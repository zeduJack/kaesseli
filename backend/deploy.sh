# Clean the old artifacts and build the application
./gradlew clean build

# Build docker container for X64 architecture
docker buildx build --platform linux/amd64 -t kaesseliacr.azurecr.io/kaesseli-api:v11 .

# azure cli login
#az login

# Run it once to get configuration for kubernetes cluster
# az aks get-credentials --resource-group rg-chote-001 --name kaesseli-cluster

# docker push image to azure
docker push kaesseliacr.azurecr.io/kaesseli-api:v11

# backend-credentials should be created once
#kubectl delete secret backend-credentials
#kubectl create secret generic backend-credentials --from-literal=FIREBASE_TOKEN='ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAia2Flc3NlbGktMThjZjgiLAogICJwcml2YXRlX2tleV9pZCI6ICI3MGM1NzM0MzkzNzkzYmQwMjA3OTZhYzhhMDJmZTQ2NjRkOGExZjI2IiwKICAicHJpdmF0ZV9rZXkiOiAiLS0tLS1CRUdJTiBQUklWQVRFIEtFWS0tLS0tXG5NSUlFdXdJQkFEQU5CZ2txaGtpRzl3MEJBUUVGQUFTQ0JLVXdnZ1NoQWdFQUFvSUJBUURSUnhPcGhFNVM4QTd4XG5scm9kRmNnSEp2TkF2MFk2aGxVc3p1eDRCQk9zTFhjNGFoWS92MzlsYjQ1QmEvVlRFVXV1U1ZzOUp1SXhEUTV3XG5VbDhIQ1BhMkZPU2c2S3RpUVg5RjdaeGcvc0kvV3lyb3JrSnByaVh0Sm1MMjJZZy94dkp6Q0VPdlNRYkttNWU2XG5sZzAxVFZteE1PYm16c1VBVHNQRjhHdU5SMjRwMzFUbFgrTUp4N0JibHZTMEtHN3FyajJTZmhkSGZnb2xVcVIvXG5hMEZDd0E4WGhQWEVGNTNNYis0ZndHb0xqQy93RWovOERWcmdWckFIU05ESW5xK1RXVmYxUGJTelhxekpVOGZDXG5lWkx0ZlkzeVZSeWNBbWVhVHMyaWxZclByQ3pndGIxbTZ2QkNMa05YZzhqeXNVNFRXWmNsMTIvQ3N3MmI3MVdvXG5taTcrbG1YcEFnTUJBQUVDZ2Y4OXBzZ092YXRWUTQzT0hnUGd1RW4xSnNSWDVBRmh2RnZiUWhvODY2RTFWOW95XG5CR1FKamtzNWdXT25ET2JOL0lwa1l3MDk0VXZyL0t5dTZqL3B2VStiZnd3d3NGcDNnbFFSaGE1TDVNRXFFNXdsXG54VEJMY2NJNmhYOGlEc2pVcTdIcnNXNC9IOW1nVm1DY2djS05CcWNsb1ZlNTBiQU0wZElGYUk1R3BkbTQrNTZhXG5wMmJ0dzg4MC9VTjVzVlpsck4yRy9aVHNtSm0yVUhTY3g3U3JnRlpkQURNeXNSc2JSb1FPaXNVRGY0UW05SnA1XG4zMHBkR3lZUmZOdzVVYStnUDMvR1dQWkJJYUhPcFZicGVRM3QyVGhCUy84MFFyN0dET2NMZ1ZHa3BVVUZ5WW1mXG53MHZycm5MMktKUytkZnRGUll5NVlNNE5DT1JMS3dEem9qRE9XajBDZ1lFQTl6N09FdTcwZlBBLzF6ZGtNL1pJXG4xYWxnSFpGeE9YeEtKdmQrb1M4Zi9PMUNFclRqWnBDUFJMaUcyTnJiaXFnVlljSFJORWd6dmFIYUY4ZmlSNE05XG56TWxORHFnT2FUTlhEOUFjdFNjbVJrSHhOb09xYTZ4ZWtSUDljTHlIZEcxSHRVSWFRSkU3NWpndWdJY3hkelNUXG5Dd2FWOHNOMzUwTEo5Z0liL3J4Q3ViVUNnWUVBMkxBYmpSRHNhcXpOTEZFUnpwZVNEcThOTXR1aVh3Zk5YZENyXG41aDJyN1VrVC80VS9FSmxZZDU4SmdKZ3BWTGZSa2MwZzhGU1pKVW1IMjZUQ1hxRzZZR0ZibnFCYWpNYUw2NFFkXG5EUkNBRXdHRXRKYkRGUDBQQ1psZnRONWQzOFBiQkRPaTJPNzRkcWxLOUhSb3VoU0NzNzBQbXFnVnVPU3VzRzI4XG5PR0p0aStVQ2dZRUE4SmNuOXBxYVJKMlFzZitQdlZTeWFWK0pUUnlEZmpubyttUXB6L1ZJZmxudHlzQkU3RUJOXG5lY1RLUzJrazY1ZEdQenRZTlRUeFRGMmNHVG9EZ2kwK3pQRXh6OHBQUTZXVzJhZGMyQmJCa3VxR0s5TVIyZmNBXG5pdEpWOUFjeEZHVm11d1lZLzYyVUNTR3dkcGVYMWZUT0JaT2lCSHFiU2VuZkYwblVMaHpLSFdVQ2dZQWxGbGRRXG4yTEd1UEc5TmczNnB2bldJUDl2aGRZZ2YvY1RmcHNTVk9VVGc4eDRTSU9zL1d3K3lRYTB0cmwxOG00MnFCOUw0XG5iM0phMkhBQlh2ekpsNksvdGtvcEowYjRTMDlOZXlwQnZ5NDhrb2oxSTh5MS9lNGg5WGRWREs1Q2kxSWtNUFpSXG5iTzFuMmVmeldTMUhsQlVlMEdBb1YxYUljOU9XYk9ISFFNbUJKUUtCZ0dDallacDNxcy9MSmMvc1Z0QjRpdVIrXG5IRlVVNElPN2JxQ3dPdnA1SCt0Wkt0anFOVDdDV2xkeGljN3Q2Z1FMOFhuRDhqNlRRL2E4V3NtNkxyaWU2WHlrXG4zRmhLb2d5ZDE4cnloYmFzaGhMR2pTNTJqTDJIQTZFYk5IMlpLQWFCcVppbmR2K3VGTDl6T3d1RTlMQkN3OFZCXG52aUxac3pWb0tKcExJRHc4OG51NFxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImZpcmViYXNlLWFkbWluc2RrLTZ1aWwwQGthZXNzZWxpLTE4Y2Y4LmlhbS5nc2VydmljZWFjY291bnQuY29tIiwKICAiY2xpZW50X2lkIjogIjExMDM0MDc5MTA1NzI5NTQ3MzQxNyIsCiAgImF1dGhfdXJpIjogImh0dHBzOi8vYWNjb3VudHMuZ29vZ2xlLmNvbS9vL29hdXRoMi9hdXRoIiwKICAidG9rZW5fdXJpIjogImh0dHBzOi8vb2F1dGgyLmdvb2dsZWFwaXMuY29tL3Rva2VuIiwKICAiYXV0aF9wcm92aWRlcl94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL29hdXRoMi92MS9jZXJ0cyIsCiAgImNsaWVudF94NTA5X2NlcnRfdXJsIjogImh0dHBzOi8vd3d3Lmdvb2dsZWFwaXMuY29tL3JvYm90L3YxL21ldGFkYXRhL3g1MDkvZmlyZWJhc2UtYWRtaW5zZGstNnVpbDAlNDBrYWVzc2VsaS0xOGNmOC5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIKfQ=='

# deploy all in azure
kubectl apply -f helm_chart.yaml