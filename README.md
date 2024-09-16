# Contacts Backend API - Kubernetes Deployment
## Project Overview
This is a Spring Boot backend API for a Contacts application. The API provides endpoints for:
- creating new contacts
- fetching existing contacts
- updating contacts
- deleting contacts

The application uses an in-memory database (H2) for storage and is containerized using Docker. This project includes the necessary Kubernetes YAML files to deploy the application on a local Kubernetes cluster using Minikube.

## Prerequisites
Before you start, ensure you have the following installed:

- Docker
- Minikube
- Kubectl

## Running the Application in Kubernetes

### Step 1: Clone the repository
```bash
git clone https://github.com/BhardwajNkl/kubernetes-contacts-api.git
cd kubernetes-contacts-api
```

### Step 2: Start Minikube
If you have not already started Minikube, use the following command to start.
```bash
minikube start
```

### Step 3: Create a Namespace
```bash
kubectl apply -f namespace.yaml
```

### Step 4: Deploy the Application
Apply the deployment and service configuration files to Kubernetes:
```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```
This does the following:
- Create a deployment that uses the public Docker image [bhardwajnkl/contacts-backend-h2:1.0.0] from Docker Hub.
- Create a Kubernetes service that exposes the application.

### Step 5: Expose the Service
Minikube doesn't expose services externally by default. So, to access the application from your host machine, you'll need to use minikube tunnel. This will expose the service on your local machine.

Run the following command **(run in a seperate terminal, as the tunnel needs to stay active)**:
```bash
minikube tunnel
```

### Step 6: Access the Application
If you have correctly followed the above steps and deployment is up and service is exposed with tunnel, you can access the Contacts API via the external IP assigned by Minikube.

To find the external IP, use:
```bash
kubectl get services -n contacts-api-namespace
```

Look for the EXTERNAL-IP and PORT(s). The external IP should be 127.0.0.1 and port 9090.

Now, you can access the Appliation. For API documentation go to: http://127.0.0.1:9090/swagger-ui/index.html

You can try the API directly from this page or you may use tools such as Postman, Curl to interact with the APIs.

### Step 7: Clean UP
To remove the service and deployment when you’re done, run:
```bash
kubectl delete -f service.yaml
kubectl delete -f deployment.yaml
kubectl delete -f namespace.yaml
```

**Stop the Minikube tunnel by pressing CTRL+C or exit the terminal.**

To stop Minikube, use the below command:
```bash
minikube stop
```
