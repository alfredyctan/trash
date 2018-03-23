#!/bin/sh


sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update
apt-cache madison docker-ce
sudo apt-get install docker-ce

sudo docker info
sudo docker --version
sudo docker run hello-world

sudo usermod -g docker atyc

cat << 'EOF' > daemon.json
{
    "insecure-registries" : [ "vm1:8181", "vm1:8182" ]
}
EOF
sudo cp daemon.json /etc/docker/daemon.json
rm daemon.json

