resource "aws_eip" "nat"{
    domain = "vpc"

    tags = {
      Name = "${local.project}-nat-${local.env}"
      project = "${local.project}"
    }
}

resource "aws_nat_gateway" "nat" {
    allocation_id = aws_eip.nat.id
    subnet_id = aws_subnet.public_zone1.id

    tags = {
        Name = "${local.project}-nat-${local.env}"
        project = "${local.project}"
    }

    # waits for the internet gateway to be created
    depends_on = [ aws_internet_gateway.igw ]
}