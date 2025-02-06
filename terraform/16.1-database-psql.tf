resource "aws_db_instance" "orders_db" {
  identifier           = "order-ms-db-${local.env}"
  engine              = "postgres"
  instance_class      = "db.t3.micro"
  allocated_storage   = 20
  db_name             = "ez_fastfood_order"
  username           = "admin"
  password           = "admin"
  publicly_accessible = false
  skip_final_snapshot = true

  vpc_security_group_ids = [aws_security_group.db_sg.id]
  db_subnet_group_name   = aws_db_subnet_group.orders_subnet.id
}

resource "aws_db_subnet_group" "orders_subnet" {
  name       = "order-subnet-group-${local.env}"
  subnet_ids = [aws_subnet.private_zone1.id, aws_subnet.private_zone2.id]

  tags = {
    Name = "orders-subnet-group-${local.env}"
    project = "${local.project}"
  }
}

resource "aws_security_group" "db_sg" {
  name        = "order-db-sg-${local.env}"
  description = "Security Group para o Banco de Orders"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    security_groups = [aws_security_group.eks_nodes.id]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
