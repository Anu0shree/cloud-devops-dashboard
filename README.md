# ☁️ Cloud DevOps Dashboard

An end-to-end DevOps project that provisions AWS infrastructure with **Terraform**, configures it with **Ansible**, containerizes a **Spring Boot** application with **Docker**, deploys it via a **GitHub Actions CI/CD pipeline**, and monitors it using **Spring Boot Actuator, Prometheus, and Grafana**.

<img width="1592" height="967" alt="image" src="https://github.com/user-attachments/assets/f00ac9cf-6fcf-4dfe-b814-c9d3dcac02e1" />

## 🏗️ Architecture

## 🏗️ Architecture

IAM User
   │
   ▼
Terraform
   │
   ▼
AWS EC2 + Elastic IP
   │
   ▼
Ansible
   │
   ▼
Docker
   │
   ▼
Spring Boot :8080
   │
   ├── Actuator
   │      │
   │      ▼
   │   Prometheus
   │      │
   │      ▼
   │   Grafana :3000
   │
   └── Dashboard UI

### 🔄 CI/CD

Git Push
    ↓
GitHub Actions
    ↓
Maven Build
    ↓
Docker Build & Push
    ↓
Docker Hub
    ↓
SSH Deployment to EC2
