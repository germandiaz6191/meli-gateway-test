#!/usr/bin/env bash
cd /home/ec2-user/server

echo 'JAVA_OPTS='$(aws ssm get-parameter --name "JAVA_OPTS" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'expirationtime='$(aws ssm get-parameter --name "expirationtime" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'gtrule1='$(aws ssm get-parameter --name "gtrule1" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'gtrule2='$(aws ssm get-parameter --name "gtrule2" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'passDB='$(aws ssm get-parameter --name "passDB" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'secretjwt='$(aws ssm get-parameter --name "secretjwt" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'urlDB='$(aws ssm get-parameter --name "urlDB" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'userDB='$(aws ssm get-parameter --name "userDB" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service
echo 'profile='$(aws ssm get-parameter --name "profile" --region us-east-1 --query "Parameter.Value" --output text) >>/home/ec2-user/server/environment.service

sudo mv environment.service /home/ec2-user/
sudo mv appservice.service /etc/systemd/system/
sudo systemctl daemon-reload
sudo systemctl reset-failed
sudo systemctl enable appservice.service

sudo systemctl start appservice.service