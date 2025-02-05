resource "aws_internet_gateway" "igw" {
  vpc_id = aws_vpc.main.id

  tags = {
    # capital N makes the tag visible by default in the AWS Console
    Name = "${local.project}-igw-${local.env}"
    project = "${local.project}"
  }
}