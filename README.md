# ☁️ Cloud DevOps Dashboard

An end-to-end DevOps project that provisions AWS infrastructure with **Terraform**, configures it with **Ansible**, containerizes a **Spring Boot** application with **Docker**, deploys it via a **GitHub Actions CI/CD pipeline**, and monitors it using **Spring Boot Actuator, Prometheus, and Grafana**.

<img width="1592" height="967" alt="image" src="https://github.com/user-attachments/assets/f00ac9cf-6fcf-4dfe-b814-c9d3dcac02e1" />

## 🏗️ Architecture

```mermaid
flowchart TD
    A[IAM User] --> B[Terraform]
    B --> C[AWS EC2 + Elastic IP]
    C --> D[Ansible]
    D --> E[Docker Container]
    E --> F[Spring Boot :8080]
    F --> G[Actuator]
    G --> H[Prometheus]
    H --> I[Grafana :3000]
```
