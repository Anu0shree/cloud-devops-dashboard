variable "aws_region" {
    description = "AWS Region"
    type       = string
}

variable "vpc_cidr" {
    description = "CIDR block for the VPC"
    type        = string
}

variable "public_subnet_cidr" {
    description = "CIDR block for the public subnet"
    type        = string
}

variable "availability_zone" {
    description = "Availability Zone for the public subnet"
    type        = string
}

variable "instance_type" {
    description = "EC2 instance type"
    type = string
}

variable "key_pair" {
    description = "key pair name for SSH access"
    type = string
}