#!/bin/bash

cd /home/ubuntu/server/server #본인의 EC2 폴더 구조에 따라 변경
sudo docker build --no-cache -t gamja/server . 
sudo chmod 755 blue-green.sh
sudo ./blue-green.sh