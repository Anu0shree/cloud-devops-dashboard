resource "aws_vpc" "devops_vpc" {
    cidr_block = var.vpc_cidr
    enable_dns_support   = true
    enable_dns_hostnames = true

    tags = {
        Name = "devops-dashboard-vpc"
    }
}

resource "aws_subnet" "public_subnet" {
    vpc_id = aws_vpc.devops_vpc.id
    cidr_block = var.public_subnet_cidr
    availability_zone = var.availability_zone
    map_public_ip_on_launch = true

    tags = {
        Name = "devops-dashboard-public-subnet"
    }
}

resource "aws_internet_gateway" "igw" {
    vpc_id = aws_vpc.devops_vpc.id

    tags = {
        Name = "devops-dashboard-igw"
    }
}

resource "aws_route_table" "public_route_table" {
    vpc_id = aws_vpc.devops_vpc.id

    route {
        cidr_block = "0.0.0.0/0"
        gateway_id = aws_internet_gateway.igw.id   
    }
    tags = {
        Name = "devops-dashboard-public-route-table"
    }
}

resource "aws_route_table_association" "public_route_table_association" {
    subnet_id      = aws_subnet.public_subnet.id
    route_table_id = aws_route_table.public_route_table.id
}

resource "aws_security_group" "devops_sg" {
    name = "devops-dashboard-sg"
    description = "Security group for DevOps Dashboard"
    vpc_id = aws_vpc.devops_vpc.id

    ingress {
        description = "SSH"
        from_port = 22
        to_port = 22
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
        description = "HTTP"
        from_port = 80
        to_port = 80
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
        description = "Spring Boot Application"
        from_port = 8080
        to_port = 8080
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
        description = "Prometheus Metrics"
        from_port = 9090
        to_port = 9090
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }

    ingress {
        description = "Grafana Dashboard"
        from_port = 3000
        to_port = 3000
        protocol = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
    egress {
        from_port = 0
        to_port = 0
        protocol = -1
        cidr_blocks = ["0.0.0.0/0"]
    }

    tags = {
        Name = "devops-dashboard-sg"
    }
}

data "aws_ami" "ubuntu" {
  most_recent = true
  owners      = ["099720109477"] # Canonical

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

resource "aws_instance" "dashboard_instance" {
    ami           = data.aws_ami.ubuntu.id
    instance_type =var.instance_type
    subnet_id     = aws_subnet.public_subnet.id
    vpc_security_group_ids = [aws_security_group.devops_sg.id]
    associate_public_ip_address = true
    key_name = var.key_pair

    tags = {
        Name = "devops-dashboard-instance"
    }
    
}

