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
