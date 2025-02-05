resource "aws_iam_role" "nodes" {
  name = "${local.eks_name}-eks-nodes-${local.env}"

  assume_role_policy = <<POLICY
  {
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": "sts:AssumeRole",
            "Principal": {
                "Service": "ec2.amazonaws.com"
            }
        }
    ]
  }
POLICY
}

# eks worker node policy
resource "aws_iam_role_policy_attachment" "amazon_eks_worker_node_policy"{
    policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
    role = aws_iam_role.nodes.name
}

# eks cni policy - manage secondary IPs for pods
resource "aws_iam_role_policy_attachment" "amazon_eks_cni_policy"{
    policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
    role = aws_iam_role.nodes.name
}

# AmazonEC2Container Registry Read Only to pull private Docker images from ECR container Registry
resource "aws_iam_role_policy_attachment" "amazon_ec2_container_registry_read_only"{
    policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
    role = aws_iam_role.nodes.name
    #role       = aws_iam_role.nodes.arn  # Change from .name to .arn
}

resource "aws_eks_node_group" "general" {
  cluster_name  = aws_eks_cluster.eks.name
  version       = local.eks_version
  node_group_name = "general"
  node_role_arn  = aws_iam_role.nodes.arn

  subnet_ids = [
    aws_subnet.private_zone1.id,
    aws_subnet.private_zone2.id
  ]

  capacity_type  = "ON_DEMAND"
  instance_types = ["t3.micro"]

  scaling_config {
    desired_size = 1
    max_size = 3
    min_size = 1
  }
  # cluster upgrades
  update_config {
    max_unavailable = 1
  }
  labels = {
    role = "general"
  }

  # wait until IAM Role and policies are created and attached
  depends_on = [ 
    aws_iam_role_policy_attachment.amazon_eks_worker_node_policy,
    aws_iam_role_policy_attachment.amazon_eks_cni_policy,
    aws_iam_role_policy_attachment.amazon_ec2_container_registry_read_only
   ]

  # allow external changes without Terraform plan difference
   lifecycle {
     ignore_changes = [ scaling_config[0].desired_size ]
   }
}