output vpc_id {
    value = aws_vpc.devops_vpc.id
}

output "public_subnet_id" {
    value = aws_subnet.public_subnet.id
}

output "internet_gateway" {
    value = aws_internet_gateway.igw.id
}

output "route_table" {
    value = aws_route_table.public_route_table.id
}

output "security_group_id" {
    value = aws_security_group.devops_sg.id
}

output "ec2_public_ip" {
    value = aws_instance.dashboard_instance.public_ip
}

output "ec2_public_dns" {
  value = aws_instance.dashboard_instance.public_dns
}