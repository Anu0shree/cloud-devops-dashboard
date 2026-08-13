# ☁️ Cloud DevOps Dashboard

An end-to-end DevOps project that provisions AWS infrastructure with **Terraform**, configures it with **Ansible**, containerizes a **Spring Boot** application with **Docker**, deploys it via a **GitHub Actions CI/CD pipeline**, and monitors it using **Spring Boot Actuator, Prometheus, and Grafana**.

<img width="1592" height="967" alt="image" src="https://github.com/user-attachments/assets/f00ac9cf-6fcf-4dfe-b814-c9d3dcac02e1" />

## 🏗️ Architecture

```mermaid
flowchart TD
    A[IAM User] --> B[Terraform]
    B --> C[AWS EC2 + Elastic IP]
    C --> D[Ansible]
    D --> E[Docker]
    E --> F[Spring Boot :8080]

    F --> G[Actuator]
    G --> H[Prometheus]
    H --> I[Grafana :3000]

    F --> J[Dashboard UI]
```

## 🔄 CI/CD

```mermaid
flowchart TD
    A[Git Push] --> B[GitHub Actions]
    B --> C[Maven Build]
    C --> D[Docker Build & Push]
    D --> E[Docker Hub]
    E --> F[SSH Deployment to EC2]
```

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java 17 | Application runtime |
| Spring Boot | Backend application |
| Thymeleaf | Server-side dashboard rendering |
| Maven | Build and dependency management |
| AWS IAM | Secure AWS authentication |
| AWS EC2 | Application hosting |
| Elastic IP | Stable public IP for EC2 |
| Terraform | Infrastructure provisioning |
| Ansible | Server configuration and automation |
| Docker | Containerization |
| Docker Hub | Docker image registry |
| GitHub Actions | CI/CD automation |
| Spring Boot Actuator | Health and application monitoring endpoints |
| Prometheus | Metrics collection |
| Grafana | Metrics visualization |

## ✨ Features

* **Infrastructure as Code** — AWS EC2 infrastructure is provisioned using Terraform instead of manually creating the server.
* **Automated Server Configuration** — Ansible is used to configure the EC2 environment and prepare it for application deployment.
* **Dockerized Application** — The Spring Boot application runs inside a Docker container for consistent deployments.
* **Automated CI/CD** — GitHub Actions builds the application, creates the Docker image, pushes it to Docker Hub, and deploys it to EC2.
* **Versioned Docker Images** — Docker images are tagged with the Git commit SHA, making each deployment traceable to a specific source-code version.
* **Stable EC2 Address** — An Elastic IP provides a consistent public address even when the EC2 instance is restarted.
* **Application Dashboard** — The custom dashboard displays application status, version, environment, port, EC2 information, and JVM statistics.
* **Application Monitoring** — Spring Boot Actuator exposes health and monitoring endpoints, with Prometheus metrics available for Grafana.
* **Grafana Visualization** — Grafana provides dashboards for monitoring application and JVM metrics.

## 📂 Project Structure

```text
cloud-devops-dashboard/
│
├── .github/
│   └── workflows/
│       └── deploy.yml                 # GitHub Actions CI/CD pipeline
│
├── ansible/
│   ├── ansible.cfg                    # Ansible configuration
│   └── deploy.yaml                    # EC2 server configuration/deployment
│
├── terraform/
│   ├── main.tf                        # EC2 infrastructure
│   ├── provider.tf                    # AWS provider configuration
│   ├── variables.tf                   # Terraform variables
│   ├── outputs.tf                     # Terraform outputs
│   ├── terraform.tfvars               # Infrastructure values
│   └── .terraform.lock.hcl            # Provider dependency lock
│
├── src/
│   ├── main/
│   │   ├── java/com/devops/dashboard/
│   │   │   ├── DashboardApplication.java
│   │   │   └── DashboardController.java
│   │   │
│   │   └── resources/
│   │       ├── templates/
│   │       │   └── dashboard.html      # Dashboard UI
│   │       └── application.properties # Application configuration
│   │
│   └── test/                           # Application tests
│
├── Dockerfile                          # Docker image configuration
├── pom.xml                             # Maven configuration
├── mvnw                                # Maven wrapper
├── mvnw.cmd                            # Maven wrapper for Windows
├── .dockerignore
├── .gitignore
└── README.md
```

## 🚀 Quick Start (Local)

```bash
git clone https://github.com/Anu0shree/cloud-devops-dashboard.git
cd cloud-devops-dashboard
mvn clean package
java -jar target/dashboard-0.0.1-SNAPSHOT.jar
```

Visit `http://localhost:8080`

**Prerequisites:** Java 17, Maven, Docker, an AWS account, Terraform CLI
