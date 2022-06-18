#!/usr/bin/env bash
chmod +x /home/ec2-user/server/* jar
chmod +x /home/ec2-user/server/server_start.sh
chmod +x /home/ec2-user/server/server_stop.sh
chmod +x /home/ec2-user/server/server_clear.sh
DIRECTORIO=/etc/systemd/system/appservice.service
ENVIRONMENT=/home/ec2-user/environment.service
if [ -f "$DIRECTORIO" ]
then
	chmod +x ${DIRECTORIO}
else
   echo "El directorio ${DIRECTORIO} no existe"
fi

if [ -f "$ENVIRONMENT" ]
then
	chmod +x ${ENVIRONMENT}
else
   echo "El directorio ${ENVIRONMENT} no existe"
fi