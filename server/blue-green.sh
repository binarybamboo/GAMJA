#!/bin/bash
EXIST_BLUE=$(docker ps | grep gamja_blue)
echo $EXIST_BLUE
if [ -z "$EXIST_BLUE" ]; then
		docker run --name gamja_blue -p 8081:8081 -td gamja/server:latest

		sleep 10
		docker kill gamja_green
        
		sleep 10
        docker rm gamja_green
        
else
        #if blue
		docker run --name gamja_green -p 8082:8081 -td gamja/server:latest
		sleep 10
		
		docker kill gamja_blue
		
        sleep 10
        docker rm gamja_blue
        
fi