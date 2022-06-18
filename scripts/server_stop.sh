#!/bin/bash
DIRECTORIO=/etc/systemd/system/appservice.service
if [ -f "$DIRECTORIO" ]
then
	sudo systemctl stop appservice.service
else
	echo "El directorio ${DIRECTORIO} no existe"
	
fi
sudo pkill -f java