#!/usr/bin/env bash

sudo rm -rf /home/ec2-user/server

ENVIRONMENT=/home/ec2-user/environment.service
if [ -f "$ENVIRONMENT" ]
then
	sudo rm ${ENVIRONMENT}
else
	echo "El directorio ${ENVIRONMENT} no existe"
fi

DIRECTORIO=/etc/systemd/system/appservice.service
if [ -f "$DIRECTORIO" ]
then
	systemctl disable appservice.service
	sudo rm ${DIRECTORIO}
else
	echo "El directorio ${DIRECTORIO} no existe"
fi

SYST=/usr/lib/systemd/system/appservice.service
if [ -f "$SYST" ]
then
	sudo rm /usr/lib/systemd/system/appservice.service
else
	echo "El directorio ${SYST} no existe"
fi